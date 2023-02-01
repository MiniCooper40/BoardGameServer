package client;

import java.io.IOException;
import java.net.Socket;

public class CheckersClient extends GameClient {
    public CheckersClient(Socket clientSocket) throws IOException {
        super(clientSocket);
    }

    @Override
    public String toString() {
        return "CheckersClient{}";
    }
}
