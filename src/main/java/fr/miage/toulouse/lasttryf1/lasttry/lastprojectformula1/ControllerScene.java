package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerScene {
    @FXML
    private TextField nomTournoi;

    @FXML
    private TextField codeInput;

    @FXML
    private DatePicker DateDeb;

    @FXML
    private DatePicker DateFin;

    @FXML
    public TableView tableViewList;

    private Stage stage;
    private Scene scene;
    private ArrayList<Tournoi> _tournoi = new ArrayList<>();
    private static final String _dataPath = "data/tournoi.json";

    /**cette methode nous permet de passer de la page d'accueil à la page Calendrier
     *
     * @param event
     * @throws IOException
     */
    public void switchToCalendrier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendrier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    /**cette methode nous permet de revenir de la page Calendrier à la page d'acceuil
     *
     * @param event
     * @throws IOException
     */
    public void switchToAccueil(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Accueil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    /** cette methode nous permet de passer de la page Calendrier à la page GrandPrix et verifie si tout les champs sont bien rentrés
     *
     * @param event
     * @throws IOException
     */
    //A VERIFIER ET A TESTER
    public void switchToAjouterGP(ActionEvent event) throws IOException {
        if ( (nomTournoi.getText() == null) || (DateDeb.getValue() == null) || (DateFin.getValue() == null ) ){
            showAlert(Alert.AlertType.ERROR, "Tous  les champs sont obligatoires");
            return;
        }
        if (DateDeb.getValue().isAfter(DateFin.getValue())){
            showAlert(Alert.AlertType.ERROR, "Erreur !La date de fin du tournoi est antérieure à la date de début du tour");
        }
        if (nomTournoi.getText() == ""){
            showAlert(Alert.AlertType.ERROR, "Le nom du tournoi est obligatoire");
        } else if (DateDeb.getValue().isAfter(DateFin.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Erreur !La date de fin du tournoi est antérieure à la date de début du tournoi.");
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createTournoi.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 560, 560);
            stage.setScene(scene);
            tabController controller = fxmlLoader.getController();
            Tournoi t = new Tournoi(nomTournoi.getText(), codeInput.getText(), DateDeb.getValue(), DateFin.getValue());
            ArrayList<Tournoi> _tournoisExistants = Tournoi.GetTournoisFromList();
            _tournoisExistants.add(t);
            Tournoi.WriteData(_tournoisExistants);
            controller.setTournoi(t);
            stage.show();
        }
    }

    /**cette methode permet l'affichage d'une allerte si les champs dans calendrier sont mal rentrés
     *
     * @param type
     * @param message
     */
    public  void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                alert.close();
            }
        });
    }

    /**afficher les anciens tournois dans une arraylist
     *
     */
    public void accederAutresTournoi()
    {
        ArrayList<Tournoi> _tournoisExistants = Tournoi.GetTournoisFromList();
        ObservableList<Tournoi> _listesGrandPrix = this.tableViewList.getItems();
        for (Tournoi tour: _tournoisExistants) {
            _listesGrandPrix.add(tour);
        }
        this.tableViewList.setItems(_listesGrandPrix);
        this.tableViewList.setVisible(true);
    }

    public void switchToTournoi(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tournoi.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }


    }


