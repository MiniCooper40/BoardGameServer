import dispatcher.GameServerDispatcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Thread(
                new GameServerDispatcher()
        ).start();
    }
}
