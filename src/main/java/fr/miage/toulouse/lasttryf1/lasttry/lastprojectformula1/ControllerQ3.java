package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerQ3 {
    private Stage stage;
    private Scene scene;

    public void switchToQ3(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }
}
