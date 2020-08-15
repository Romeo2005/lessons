package lesson15Chat.server;

public interface AuthService {
    void start();

    void stop();

    void getNameByLoginPass(String login, String pass);
}
