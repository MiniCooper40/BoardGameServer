package server.io;

import communication.formats.NetworkMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkOutputStream {

    private final ObjectOutputStream objectOutputStream;

    public NetworkOutputStream(OutputStream outputStream) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(outputStream);
    }

    public void sendMessage(NetworkMessage message) throws IOException {
        objectOutputStream.writeObject(message);
    }

    public void close() throws IOException {
        objectOutputStream.close();
    }

    public static NetworkOutputStream forSocket(Socket clientSocket) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        return new NetworkOutputStream(outputStream);
    }
}
