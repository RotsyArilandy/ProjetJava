package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;

import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;

public class ControllerQ2 {
    private Stage stage;
    private Scene scene;
    static ArrayList<Pilote> _pilote = new ArrayList<>();
    ArrayList<Object> labels = new ArrayList<>();
    ArrayList<TextField> champs = new ArrayList<>();
    public TableView<Pilote> tableView;

    @FXML
    public TableColumn<Pilote, String> pilote;

    @FXML
    public TableColumn<Pilote, Long> temps;

    @FXML
    private VBox container;

    private Tournoi _tournoi;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        pilote.setCellValueFactory(new PropertyValueFactory<Pilote, String>("nomPilote"));

    }

    public void setTournoi(Tournoi tournoi) {
        Comparator<Pilote> comparator = Comparator.comparing(Pilote::getTemps);

        Collections.sort(ControllerQ1._pilote, comparator);
        for (int i = 0; i < ControllerQ1._pilote.size()-5; i++) {
            this._pilote.add(ControllerQ1._pilote.get(i));

        }
        for (int i = 0; i < this._pilote.size(); i++) {
            Label label = new Label("Label " + i);
            TextField champ = new TextField();
            labels.add(label);
            champs.add(champ);
            container.getChildren().add(label);
            container.getChildren().add(champ);
            label.setText(this._pilote.get(i).getNomPilote());

        }
    }

    public void trier() {
        //lien entre le textfiled et les pilote
        for (int i = 0; i < _pilote.size()-5; i++) {
            _pilote.get(i).temps = parseLong(champs.get(i));
        }
        Comparator<Pilote> comparator = Comparator.comparing(Pilote::getTemps);

        Collections.sort(_pilote, comparator);
        ObservableList<Pilote> obEcurie = tableView.getItems();
        for (int i = 0; i < _pilote.size()-5; i++) {
            obEcurie.add(_pilote.get(i));
        }
        tableView.setItems(obEcurie);
    }

    public void switchToQ3(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        ControllerQ3 q3Controller = fxmlLoader.getController();
        this._tournoi= tabEcurieController._tournoi;
        this._tournoi.ecuries = tabEcurieController._ecuries;
        q3Controller.setTournoi(this._tournoi);
        Tournoi actuel = Tournoi.GetTournoiByCode(this._tournoi.codeTournoi);
        if(actuel != null) {
            // si la liste des écuries est vide, on initialise
            if (actuel.ecuries == null)
                actuel.ecuries = new ArrayList<>();

            actuel.ecuries.addAll(tabEcurieController._ecuries);
            ArrayList<Tournoi> tournois = Tournoi.SetTournoiInList(actuel);
            Tournoi.WriteData(tournois);
        }
        stage.show();
    }

    public static Long parseLong(TextField textField) {
        String inputText = textField.getText();
        try {
            return Long.parseLong(inputText);
        } catch (NumberFormatException e) {
            return null; // renvoie null si le texte ne peut pas être analysé en Long
        }

    }

    public void switchToQ1(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

}
