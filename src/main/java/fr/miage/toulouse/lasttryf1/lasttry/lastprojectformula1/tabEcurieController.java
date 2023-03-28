package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.*;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class tabEcurieController implements Initializable {

    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Ecurie> tableView;

    @FXML
    private TableColumn<Ecurie,String>  ecurie;

    @FXML
    private TableColumn<Ecurie,String> pilote1;

    @FXML
    private TableColumn<Ecurie,String> pilote2;

    @FXML
    private TextField ecurieInput;
    @FXML
    private VBox container;
    @FXML
    private TextField pilote1Input;

    @FXML
    private TextField pilote2Input;

    @FXML
    private Button Suivant;

    public static ArrayList<Ecurie> _ecuries;
    public static Tournoi _tournoi;

    int nbClick = 0;

    public tabEcurieController()
    {
        _ecuries = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ecurie.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("ecurie"));
        pilote1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ecurie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Ecurie, String> ecu) {
                return new SimpleStringProperty(ecu.getValue().getPilote1().getNomPilote());
            }
        });
        pilote2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ecurie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Ecurie, String> ecu) {
                return new SimpleStringProperty(ecu.getValue().getPilote2().getNomPilote());
            }
        });
    }

    @FXML
    void submit (ActionEvent e){

        if (nbClick < 10){
            Ecurie E = new Ecurie(ecurieInput.getText(),(new Pilote(pilote1Input.getText())), (new Pilote(pilote2Input.getText())));
            ObservableList <Ecurie> obEcurie= tableView.getItems();
            obEcurie.add(E);
            tableView.setItems(obEcurie);
            _ecuries.add(E);
            nbClick++;
        }
    }
    @FXML
    void removeGrandPrix (ActionEvent event ){
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }

    /** @FXML
    void recupDonnees (ActionEvent event){
    for (int i = 0; i< 10; i++){
    ecurie x = _ecuries.get(i);
    tableView.
    }
    }**/

    public void switchToTournois(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    /**
     Cette méthode remplit la TableView des écuries avec les données de la liste des écuries.
     */
    private void setEcurieTableView()  {
        ObservableList <Ecurie> obEcurie = tableView.getItems();

        for (Ecurie e: _ecuries)
            obEcurie.add(e);

        tableView.setItems(obEcurie);
    }
    /**
     La méthode switchToQ1 charge la page Q1.fxml dans la fenêtre principale
     Initialise le contrôleur associé.
     Elle met également à jour l'état actuel du tournoi en y ajoutant les écuries sélectionnées.
     @throws IOException si une erreur se produit lors du chargement de la page FXML
     */
    public void switchToQ1(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        ControllerQ1 q1Controller = fxmlLoader.getController();
        _tournoi.ecuries = _ecuries;
        q1Controller.setTournoi(_tournoi);
        Tournoi actuel = Tournoi.GetTournoiByCode(_tournoi.codeTournoi);
        if(actuel != null) {
            // si la liste des écuries est vide, on initialise
            if (actuel.ecuries == null)
                actuel.ecuries = new ArrayList<>();

            actuel.ecuries.addAll(_ecuries);
            ArrayList<Tournoi> tournois = Tournoi.SetTournoiInList(actuel);
            Tournoi.WriteData(tournois);
        }
        stage.show();
    }
    public void setTournoi(Tournoi tournoi)
    {
        _tournoi = tournoi;
    }
}