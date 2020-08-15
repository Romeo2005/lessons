package lesson15Chat.server;

import lesson15Chat.Message;
import lesson15Chat.MessageConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyServer {
    private final ServerSocket serverSocket;
    private final int PORT = 8080;
    private final Map<String, Account> subscribers = new HashMap<>();
    private final MainAuthService authService;

    public MyServer() throws IOException {
        authService = new MainAuthService(this);
        serverSocket = new ServerSocket(PORT);
        Thread newClientListener = new Thread(this::listenToNewClients);
        newClientListener.start();
    }

    private void listenToNewClients() {
        System.out.println("Server started");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("New connection");
                Thread newClientThread = new Thread(() -> {
                    try {
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        output.flush();
                        while (true) {
                            try {
                                Message message = (Message) input.readObject();
                                if (message.getType() == MessageConstants.AUTH) {
                                    String name = authService.authorise(new Account(message.getLogin(), message.getPass()), socket, output);
                                    if (name != null) {
                                        System.out.println(name + " Connected");
                                        output.writeObject(new Message(MessageConstants.ACCEPTED, name));
                                        message.setFrom(name);
                                        sendMessageToAllUsers(new Message(MessageConstants.AUTH, name, null, null));
                                        sendUsersInfo(output);
                                    } else {
                                        System.out.println(message.getFrom() + " wasn't accepted");
                                        output.writeObject(new Message(MessageConstants.NOT_ACCEPTED, null));
                                    }
                                    break;
                                }
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
                newClientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendUsersInfo(ObjectOutputStream output) throws IOException {
        for (Account acc : subscribers.values()) {
            output.writeObject(new Message(MessageConstants.AUTH, acc.getName(), null, null));
        }
    }

    public void notifyAboutClientMessage(Message message) throws IOException {
        if (message.getType() == MessageConstants.SIMPLE)
            if (message.getTo().equals("ALL"))
                sendMessageToAllUsers(message);
            else {
                for (Account acc : subscribers.values()) {
                    if (message.getTo().equals(acc.getName()))
                        acc.getNetwork().sendMessageToThisClient(message);
                }
            }
    }

    private void sendMessageToAllUsers(Message message) throws IOException {
        for (Account account : subscribers.values())
            account.getNetwork().sendMessageToThisClient(message);
    }

    public NetworkClient requestClient(Account account, Socket connection) {
        subscribers.put(account.getLogin(), account);
        return new NetworkClient(account.getName(), connection, this);
    }

    void deleteSubscriber(String s) {
        subscribers.remove(s);
        try {
            sendMessageToAllUsers(new Message(MessageConstants.EXIT, s, null, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
