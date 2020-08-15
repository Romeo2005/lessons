package lesson15Chat.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainAuthService implements AuthService {
    public ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public void start() {
        System.out.println("Service started");
    }

    @Override
    public void stop() {
        System.out.println("Service stopped");
    }

    @Override
    public void getNameByLoginPass(String login, String pass) {

    }

    public MainAuthService(MyServer server) throws IOException {
        accounts.add(new Account("Nick1", "login1", "pass1", server));
        accounts.add(new Account("Nick2", "login2", "pass2", server));
        accounts.add(new Account("Nick3", "login3", "pass3", server));
    }

    public String authorise(Account account, Socket connection, ObjectOutputStream output) throws IOException {
        for (Account acc : accounts) {
            if (acc.equals(account) && !account.isAuthorised()) {
                int index = accounts.indexOf(account);
                accounts.get(index).addClient(connection);
                accounts.get(index).getNetwork().setOutput(output);
                return acc.getName();
            }
        }
        return null;
    }
}
