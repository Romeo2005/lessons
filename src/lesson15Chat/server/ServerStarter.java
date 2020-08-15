package lesson15Chat.server;

import java.io.IOException;

public class ServerStarter {
    public static void main(String[] args) {
        try {
            new MyServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
