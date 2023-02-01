package server.servers;

import client.CheckersClient;
import client.GameClient;
import communication.constants.games.GameType;
import communication.constants.network.RequestStatus;
import communication.constants.players.Player;
import communication.formats.GameRequest;
import communication.formats.NetworkMessage;
import game.moves.types.AllGameItems;
import game.moves.types.GameStateChange;
import game.pieces.GameItems;
import game.state.CheckersGameState;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EnumMap;
import java.util.Map;

public class CheckersServer extends GameServer{

    private static final int PORT = 5784;

    private final Map<Player, GameClient> players;
    private final ServerSocket serverSocket;
    private final CheckersGameState gameState;

    public CheckersServer() throws IOException {
        players = new EnumMap<>(Player.class);
        serverSocket = new ServerSocket(PORT);
        gameState = new CheckersGameState();
    }

    @Override
    public boolean canHoldMoreClients() {
        return numPlayers() == 0;
    }

    @Override
    public GameRequest getClientGameRequest() {
        Player nextPlayer = nextPlayer();
        return gameRequestForPlayer(nextPlayer);
    }

    private GameRequest gameRequestForPlayer(Player player) {
        return new GameRequest(
                GameType.CHECKERS, player, PORT
        );
    }

    @Override
    public void acceptClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Accepted client in CS");
        GameClient gameClient = new CheckersClient(clientSocket);
        players.put(nextPlayer(), gameClient);
        System.out.println("Players: " + players);
    }

    @Override
    public void run() {
        System.out.println("In CheckersServer run!!");
        startGame();
        sendCompleteState();
    }

    private Player nextPlayer() {
        int numOfNextPlayer = numPlayers()+1;
        return Player.fromNumber(numOfNextPlayer);
    }

    private int numPlayers() {
        return players.size();
    }

    private void sendCompleteState() {
        GameItems items = gameState.getGameItems();
        GameStateChange stateChange = new AllGameItems(items);
        dispatchStateChange(stateChange);
    }

    private void startGame() {
        NetworkMessage message = new NetworkMessage.Builder()
                .status(RequestStatus.GAME_START)
                .build();
    }

    private void dispatchMessage(NetworkMessage message) {
        for(GameClient client : players.values()) {
            try {
                client.sendUpdate(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void dispatchStateChange(GameStateChange stateChange) {
        for(GameClient client : players.values()) {
            try {
                client.sendStateChange(stateChange);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
