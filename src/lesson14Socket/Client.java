package lesson14Socket;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Scanner scanner = new Scanner(System.in);
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private final static int PORT = 8080;
    private final static String EXIT_STRING = "/exit";
    private final static String INDENT = "          ";

    private static final Thread messageSender = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    output.flush();
                    String sendMessage = scanner.nextLine();
                    output.writeObject(sendMessage);
                    if (sendMessage.equals(EXIT_STRING))
                        System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private static final Thread messageListener = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String inputString = (String)input.readObject();
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

    public static void main(String[] args) {
        try {
            initialize();
            messageListener.start();
            messageSender.start();
        } catch (IOException | InterruptedException e) {
            System.out.println("Cached an exception");
            System.exit(0);
        }
    }

    private static void initialize() throws IOException, InterruptedException {
        Socket socket;
        while (true) {
            try {
                socket = new Socket("127.0.0.1", 8080);
                break;
            } catch (ConnectException ignored){
                System.out.println("Server isn't working\nReconnection in a second");
                Thread.sleep(1000);
            }
        }
        System.out.println("Connected to " + socket.getInetAddress() + "\n***************************************\n");
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }
}
