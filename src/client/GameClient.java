package client;

import communication.constants.network.RequestStatus;
import communication.formats.NetworkMessage;
import game.moves.types.GameStateChange;
import server.io.NetworkInputStream;
import server.io.NetworkOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class GameClient {

    private final Socket clientSocket;
    private final NetworkOutputStream networkOutput;
    private final NetworkInputStream networkInput;

    protected GameClient(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        networkOutput = NetworkOutputStream.forSocket(clientSocket);
        networkInput = NetworkInputStream.forSocket(clientSocket);
    }

    public NetworkMessage awaitUpdate() throws IOException, ClassNotFoundException {
        return networkInput.readMessage();
    }

    public void sendStateChange(GameStateChange stateChange) throws IOException {
        NetworkMessage message = new NetworkMessage.Builder()
                .stateChange(stateChange)
                .status(RequestStatus.UPDATE)
                .build();
        sendUpdate(message);
    }

    public void sendUpdate(NetworkMessage message) throws IOException {
        networkOutput.sendMessage(message);
    }

    public void disconnect() throws IOException {
        clientSocket.close();
        networkOutput.close();
        networkInput.close();
    }
}
