package server.io;

import communication.formats.GameRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GameRequestOutputStream {
    private final ObjectOutputStream objectOutputStream;

    public GameRequestOutputStream(OutputStream outputStream) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(outputStream);
    }

    public void sendRequest(GameRequest gameRequest) throws IOException {
        objectOutputStream.writeObject(gameRequest);
    }

    public void close() throws IOException {
        objectOutputStream.close();
    }

    public static GameRequestOutputStream forSocket(Socket socket) throws IOException {
        OutputStream socketOutputStream = socket.getOutputStream();
        return new GameRequestOutputStream(socketOutputStream);
    }
}
