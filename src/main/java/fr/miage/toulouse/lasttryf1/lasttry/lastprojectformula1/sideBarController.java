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

public class sideBarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private void home (MouseEvent event){
        bp.setCenter(ap);
        loadPage("home");

    }

    @FXML
    private void createTournoi(ActionEvent event) {
        loadPage("createTournoi");
    }

    
    @FXML
    private void tournoi(MouseEvent event){
        loadPage("tournoi");
    }

    @FXML
    private void ecurie (MouseEvent event){
        loadPage("ecurie");
    }

    @FXML
    private void qualification(MouseEvent event){
        loadPage("qualification");
    }

    @FXML
    private void course(MouseEvent event){
        loadPage("course");
    }

    @FXML
    private void resultat (MouseEvent event){
        loadPage("resultat");
    }

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
