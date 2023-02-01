package server.servers;

import client.GameClient;
import communication.constants.games.GameType;
import communication.formats.GameRequest;
import server.io.GameRequestOutputStream;

import java.io.IOException;
import java.net.Socket;

public abstract class GameServer implements Runnable {

    public abstract boolean canHoldMoreClients();
    public abstract GameRequest getClientGameRequest();
    public abstract void acceptClient() throws IOException;
    public void inviteNewClient(GameRequestOutputStream clientOutput) throws IOException {
        GameRequest gameRequest = getClientGameRequest();
        System.out.println("Sending request: " + gameRequest);
        clientOutput.sendRequest(gameRequest);
    }

    public static GameServer forGameType(GameType type) throws IOException {
        return switch (type) {
            case CHECKERS ->  new CheckersServer();
        };
    }
}
