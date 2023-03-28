package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 La classe sideBarController est responsable de la gestion de la barre de navigation latérale dans l'application.
 Elle charge différentes pages FXML dans le centre du BorderPane de la scène principale.
 */
public class sideBarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     Charge la page FXML "home" dans le centre du BorderPane de la scène principale.
     */
    @FXML
    private void home (MouseEvent event){
        bp.setCenter(ap);
        loadPage("home");

    }
    /**
     Charge la page FXML "createTournoi" dans le centre du BorderPane de la scène principale.
     */
    @FXML
    private void createTournoi(ActionEvent event) {
        loadPage("createTournoi");
    }

    /**
    Charge la page FXML "tournoi" dans le centre du BorderPane de la scène principale.
    */
    @FXML
    private void tournoi(MouseEvent event){
        loadPage("tournoi");
    }

    /**
     Charge la page FXML "ecurie" dans le centre du BorderPane de la scène principale.
     */
    @FXML
    private void ecurie (MouseEvent event){
        loadPage("ecurie");
    }

    /**
     Charge la page FXML "qualification" dans le centre du BorderPane de la scène principale.
     */
    @FXML
    private void qualification(MouseEvent event){
        loadPage("qualification");
    }

    /**
    Charge la page FXML "course" dans le centre du BorderPane de la scène principale.
    */
    @FXML
    private void course(MouseEvent event){
        loadPage("course");
    }

    /**
     Charge la page FXML "resultat" dans le centre du BorderPane de la scène principale.
     */
    @FXML
    private void resultat (MouseEvent event){
        loadPage("resultat");
    }
    /**
     Charge une page FXML dans le centre du BorderPane de la scène principale.
     @throws RuntimeException si une erreur se produit lors du chargement de la page FXML
     */
    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(root);

    }

}
