package lesson15Chat.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import lesson15Chat.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Controller {
    private static Client client;

    @FXML
    private VBox nickBox;

    @FXML
    private TextArea viewMessageTEXT;

    @FXML
    private TextArea sendMessageTEXT;

    public static Client getClient() {
        return client;
    }

    @FXML
    void initialize() throws IOException {
        client = new Client(this);
    }
    public void addUser(NickField field) {
        nickBox.getChildren().add(field.getField());
    }

    public void addMessage(Message message) {
        viewMessageTEXT.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        viewMessageTEXT.appendText(message.getText() + '\n');
    }


    public void sendButtonPressed() {
        client.sendSimpleMessage(sendMessageTEXT.getText());
        sendMessageTEXT.clear();
    }

    public void deleteUser(NickField field) {
        Platform.runLater(() -> nickBox.getChildren().remove(field.getField()));
    }
}
