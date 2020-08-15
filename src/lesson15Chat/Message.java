package lesson15Chat;

import lesson15Chat.server.Account;

import java.io.Serializable;

public class Message implements Serializable {
    private String from;
    private String text;
    private final lesson15Chat.MessageConstants type;
    private String readyMessage;
    private String login;
    private String pass;
    private String to;

    public Message(MessageConstants type, String from, String to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.type = type;

        //formMessage();
    }

    public Message(MessageConstants type, String login, String pass) {
        //formMessage();
        this.type = type;
        this.login = login;
        this.pass = pass;
    }

    public Message(MessageConstants type, String name) {
        this.type = type;
        this.to = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getText() {
        return from + ": " + text;
    }

    public lesson15Chat.MessageConstants getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
