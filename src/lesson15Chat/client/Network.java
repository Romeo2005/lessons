package lesson15Chat.client;

import javafx.application.Platform;
import lesson15Chat.Message;
import lesson15Chat.MessageConstants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network {
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static Client client;
    private static Controller controller;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;
    private static Socket connection;
    private boolean isAuthorised = false;

    public Network(Client client, Controller controller) {
        Network.client = client;
        Network.controller = controller;
    }

    public void sendMessage(Message message) throws IOException {
        if (!isAuthorised)
            output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        output.writeObject(message);
        controller.addMessage(message);
        isAuthorised = true;
    }

    private static void listenToMessages() {
        while (true) {
            Message message;
            try {
                message = (Message) input.readObject();
                if (message.getFrom() != null) {
                    if (!message.getFrom().equals(client.getName())) {
                        if (message.getType().equals(MessageConstants.SIMPLE))
                            controller.addMessage(message);
                        else if (message.getType() == MessageConstants.AUTH)
                            Platform.runLater(() -> client.addNick(message.getFrom()));
                        else if (message.getType() == MessageConstants.EXIT)
                            client.deleteNick(message.getFrom());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String sendAuthMessage(String login, String password) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(HOST, PORT);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        out.writeObject(new Message(MessageConstants.AUTH, login, password));
        Message message = (Message) in.readObject();
        if (message.getType() == MessageConstants.ACCEPTED) {
            connection = socket;
            input = in;
            client.setName(message.getTo());
            new Thread(Network::listenToMessages).start();
            return message.getTo();
        } else {
            return null;
        }

    }
}
