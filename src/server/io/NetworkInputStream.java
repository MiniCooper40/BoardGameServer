package server.io;

import communication.formats.NetworkMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkInputStream {

    private final ObjectInputStream objectInputStream;

    public NetworkInputStream(InputStream inputStream) throws IOException {
        this.objectInputStream = new ObjectInputStream(inputStream);
    }

    public NetworkMessage readMessage() throws IOException, ClassNotFoundException {
        return (NetworkMessage) objectInputStream.readObject();
    }

    public void close() throws IOException {
        objectInputStream.close();
    }

    public static NetworkInputStream forSocket(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        return new NetworkInputStream(inputStream);
    }
}
