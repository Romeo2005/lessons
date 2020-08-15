package lesson15Chat.client;

public enum Scenes {
    MAIN("resources/ClientView.fxml"), AUTHORISATION("resources/Authorisation.fxml");
    private final String root;

    Scenes(String root) {
        this.root = root;
    }

    public String getRoot() {
        return root;
    }
}
