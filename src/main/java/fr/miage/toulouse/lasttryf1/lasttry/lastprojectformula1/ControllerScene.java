package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerScene {
    @FXML
    private TextField nomTournoi;

    @FXML
    private DatePicker DateDeb;

    @FXML
    private DatePicker DateFin;

    private Stage stage;
    private Scene scene;
    private ArrayList<Tournoi> _tournoi = new ArrayList<>();
    private static final String _dataPath = "data/tournoi.json";
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

    //A VERIFIER ET A TESTER
    public void switchToAjouterGP(ActionEvent event) throws IOException {
        if (nomTournoi.getText() == ""){
            showAlert(Alert.AlertType.ERROR, "Le nom du tournoi est obligatoire");
        } else if (DateDeb.getValue().isAfter(DateFin.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Erreur !La date de fin du tournoi est antérieure à la date de début du tournoi.");
        } //si tous les champs ne sont pas remplis
        else if ((DateDeb.getValue() == null) || (DateFin.getValue() == null)){
            showAlert(Alert.AlertType.ERROR, "Complétez tous les champs ");
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createTournoi.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 560, 560);
            stage.setScene(scene);
            stage.show();
            save(event);
        }
    }

    public  void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                alert.close();
            }
        });
    }

    //A REVOIR ET A RETESTER
        void save (ActionEvent event ){
            Tournoi t = new Tournoi(nomTournoi.getText(), DateDeb.getValue(), DateFin.getValue());
            JSONObject obj = new JSONObject(t);
            String objetString  = obj.toString();
            _tournoi.add(t);
            try {
                String finalString = "[";
                finalString += String.join(",", objetString);
                finalString+="]";
                File file = new File(_dataPath);
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write(finalString);
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    public void switchToListeTournoi(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tournoi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }
    }


