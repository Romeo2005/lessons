package lesson15Chat.client;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextField;


public class NickField {
    private final TextField field;

    public NickField(String name) {
        field = new TextField(name);
        field.setEditable(false);
        field.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        field.setOnMouseClicked(event -> Controller.getClient().setGetter(field.getText()));
    }

    public TextField getField() {
        return field;
    }

    public void setActive(boolean b) {
        if (b) {
            field.setStyle("-fx-background-color: #0000ff");
        } else {
            field.setStyle("-fx-background-color: #fafafa");
        }
       // field.setStyle("-fx-border-color: #000");
    }

    @Override
    public boolean equals(Object obj) {
        NickField field = (NickField) obj;
        return this.getField().getText().equals(field.getField().getText());
    }
}
