package lesson14Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    private static final Scanner scanner = new Scanner(System.in);
    private final static int PORT = 8080;
    private final static String EXIT_STRING = "/exit";
    private final static String INDENT = "          ";
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    private static final Thread messageListener = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String inputString = (String) input.readObject();
                    if (inputString.equals(EXIT_STRING))
                        System.exit(0);
                    System.out.println(INDENT + inputString);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Cached an exception");
                    System.exit(0);
                }
            }
        }
    });

    private static final Thread messageSender = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    output.flush();
                    String sendStr = scanner.nextLine();
                    output.writeObject(sendStr);
                    if (sendStr.equals(EXIT_STRING))
                        System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public static void main(String[] args) {
        try {
            initialize();
            messageListener.start();
            messageSender.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initialize() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting For client");
        Socket connection = serverSocket.accept();
        System.out.println("connected to "+ connection.getInetAddress() + "\n***************************************\n");
        output = new ObjectOutputStream(connection.getOutputStream());
        input = new ObjectInputStream(connection.getInputStream());
    }

}
