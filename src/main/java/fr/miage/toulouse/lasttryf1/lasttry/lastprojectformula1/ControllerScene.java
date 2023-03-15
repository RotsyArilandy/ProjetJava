package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerScene {
    @FXML
    private DatePicker DateDeb;

    @FXML
    private DatePicker DateFin;

    private Stage stage;
    private Scene scene;
    public void switchToCalendrier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendrier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAccueil(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Accueil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAjouterGP(ActionEvent event) throws IOException {
        if (DateDeb.getValue().isAfter(DateFin.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Erreur !La date de fin du tournoi est antérieure à la date de début du tournoi.");

        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createTournoi.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 560, 560);
            stage.setScene(scene);
            stage.show();
        }
    }

    public  void showAlert(Alert.AlertType type, String message){
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                alert.close();
            }
        });
    }

}
