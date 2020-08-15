package lesson15Chat.server;


import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Account implements Serializable {
    private final String name;
    private final String login;
    private final String pass;
    private final MyServer server;
    private NetworkClient network = null;

    public void addClient(Socket connection) throws IOException {
        this.network = server.requestClient(this, connection);
    }

    public Account(String name, String login, String pass, MyServer server) throws IOException {
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.server = server;
    }

    public Account(String login, String pass) throws IOException {
        this(null, login, pass, null);
    }

    public String getName() {
        return name;
    }

    public boolean isAuthorised() {
        return network != null;
    }

    @Override
    public boolean equals(Object obj) {
        Account account = (Account) obj;
        return account.login.equals(this.login) && account.pass.equals(this.pass);
    }

    public NetworkClient getNetwork() {
        return network;
    }

    public void deleteNetwork() {
        this.network = null;
    }

    public String getLogin() {
        return this.login;
    }
}