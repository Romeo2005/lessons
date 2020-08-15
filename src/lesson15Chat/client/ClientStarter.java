package lesson15Chat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ClientStarter extends Application {
    private Scene mainSENE;
    private Scene authSCENE;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        Parent auth = FXMLLoader.load(getClass().getResource(Scenes.AUTHORISATION.getRoot()));
        Parent root = FXMLLoader.load(getClass().getResource(Scenes.MAIN.getRoot()));
        mainSENE = new Scene(root, 900, 600);
        authSCENE = new Scene(auth, 900, 600);
        stage.setTitle("Chat");
        stage.setScene(authSCENE);
        stage.show();
        AuthorisationController.setStarter(this);
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadScene(Scenes scene) {
        if (scene == Scenes.AUTHORISATION)
            stage.setScene(authSCENE);
        else
            stage.setScene(mainSENE);
    }
}
