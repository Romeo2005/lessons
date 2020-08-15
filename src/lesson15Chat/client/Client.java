package lesson15Chat.client;

import javafx.stage.Stage;
import lesson15Chat.Message;
import lesson15Chat.MessageConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private final Network network;
    private String name;
    private String getter = "ALL";
    private final Controller controller;
    private final Map<String, NickField> nicks = new HashMap<>();

    public Client(Controller controller) {
        this.controller = controller;
        this.network = new Network(this, controller);
    }

    public String getName() {
        return name;
    }

    public void sendSimpleMessage(String text) {
        try {
            network.sendMessage(new Message(MessageConstants.SIMPLE, name, getter, text));
            if (!getter.equals("ALL")) {
                nicks.get(getter).setActive(false);
                getter = "ALL";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGetter(String getter) {
        if (!this.getter.equals(getter)) {
            for (String s : nicks.keySet())
                nicks.get(s).setActive(false);
            nicks.get(getter).setActive(true);
            this.getter = getter;
        } else {
            this.getter = "ALL";
            nicks.get(getter).setActive(false);
        }
    }

    public void addNick(String name) {
        NickField field = new NickField(name);
        nicks.put(name, field);
        controller.addUser(field);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void deleteNick(String name) {
        controller.deleteUser(nicks.get(name));
        nicks.remove(name);
    }
}
