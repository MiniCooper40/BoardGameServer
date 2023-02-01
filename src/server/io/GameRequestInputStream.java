package server.io;

import communication.formats.GameRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class GameRequestInputStream {
    private final ObjectInputStream objectInputStream;

    public GameRequestInputStream(InputStream inputStream) throws IOException {
        this.objectInputStream = new ObjectInputStream(inputStream);
    }

    public GameRequest readRequest() throws IOException, ClassNotFoundException {
        return (GameRequest) objectInputStream.readObject();
    }

    public void close() throws IOException {
        objectInputStream.close();
    }

    public static GameRequestInputStream forSocket(Socket socket) throws IOException {
        InputStream socketInputStream = socket.getInputStream();
        return new GameRequestInputStream(socketInputStream);
    }
}
