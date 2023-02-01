package dispatcher;

import communication.constants.games.GameType;
import communication.formats.GameRequest;
import server.io.GameRequestInputStream;
import server.io.GameRequestOutputStream;
import server.servers.GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameServerDispatcher implements Runnable {

    private static final int PORT = 5503;
    private static final String HOSTNAME = "127.0.0.1";

    private boolean running = true;
    private ServerSocket serverSocket;
    private Map<GameType, GameServer> gameServers;
    private Set<Thread> activeGameThreads;

    public GameServerDispatcher() throws IOException {
        gameServers = new EnumMap<>(GameType.class);
        serverSocket = new ServerSocket(PORT);
        activeGameThreads = new HashSet<>();
    }

    @Override
    public void run() {
        while(running) {
            System.out.println("In dispatcher, run()");
            dispatchNextClient();
        }
    }

    private void dispatchNextClient() {
        System.out.println("In dispatchNextClient");
        try {
            Socket clientSocket = serverSocket.accept();

            GameRequestInputStream clientInput = GameRequestInputStream.forSocket(clientSocket);
            GameRequestOutputStream clientOutput = GameRequestOutputStream.forSocket(clientSocket);

            GameType gameType = findRequestedGameType(clientInput);
            assignClientToGame(clientOutput, gameType);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private GameType findRequestedGameType(GameRequestInputStream clientInput) throws IOException, ClassNotFoundException {
        GameRequest clientRequest = clientInput.readRequest();
        System.out.println("Received game request: " + clientRequest);
        return clientRequest.getGameType();
    }

    private void assignClientToGame(GameRequestOutputStream clientOutput, GameType gameType) {
        try {
            GameServer gameServer = createOrFindServerFor(gameType);
            gameServer.inviteNewClient(clientOutput);
            gameServer.acceptClient();
            startGameIfEligible(gameType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private GameServer createOrFindServerFor(GameType gameType) throws Exception {
        if(gameServers.containsKey(gameType))
            return gameServers.get(gameType);
        try {
            GameServer server = GameServer.forGameType(gameType);
            gameServers.put(gameType, server);
            return server;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void startGameIfEligible(GameType type) {
        GameServer server = gameServers.get(type);
        if(!server.canHoldMoreClients()) {
            gameServers.remove(type);
            startGameServer(server);
        }
    }

    private void startGameServer(GameServer server) {
        Thread gameThread = new Thread(server);
        activeGameThreads.add(gameThread);
        gameThread.start();
    }
}
