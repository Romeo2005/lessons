package lesson15Chat.server;

import lesson15Chat.Message;

import java.io.*;
import java.net.Socket;

public class NetworkClient implements Serializable {
    private final Socket socket;
    private final String name;
    private ObjectOutputStream output;
    private final MyServer server;
    private boolean hasClientExited = false;

    public NetworkClient(String name, Socket socket, MyServer server) {
        this.name = name;
        this.socket = socket;
        this.server = server;
        Thread inputMessagesListener = new Thread(this::listenMessage);
        inputMessagesListener.start();
    }

    private void listenMessage() {
        Message message;
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            while (!hasClientExited) {
                    message = (Message) input.readObject();
                    System.out.println(message.getText());
                    server.notifyAboutClientMessage(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            hasClientExited = true;
            server.deleteSubscriber(name);
            System.out.println(name + " disconnected");
        }
    }

    public void sendMessageToThisClient(Message message) throws IOException {
        if (!hasClientExited) {
            output.flush();
            output.writeObject(message);
        }
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }
}
