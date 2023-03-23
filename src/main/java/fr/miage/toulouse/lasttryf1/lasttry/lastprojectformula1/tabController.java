package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;
// Ce controller est le controller de  la page ajouter GP
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
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.CharBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class tabController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<GrandPrix> tableView;

    @FXML
    private TableColumn<GrandPrix,String>  name;

    @FXML
    private TableColumn<GrandPrix,String> pays;

    @FXML
    private TableColumn<GrandPrix,LocalDate> date;

    @FXML
    private TextField nomInput;

    @FXML
    private TextField codeInput;

    @FXML
    private TextField paysInput;

    @FXML
    private DatePicker dateInput;

    @FXML
    private Label champTournoi;

    @FXML
    private TextField inputNomTournoi;

    @FXML
    private DatePicker inputDateDep;

    @FXML
    private DatePicker inputDateFin;

    private Tournoi _tournoi;
    private ArrayList<GrandPrix> _lstGrandPrix;

    //A voir pourquoi ça ne s'affiche pas enconre
    public void recupNomTournoi(){
        if(!inputDateFin.getValue().isAfter(inputDateDep.getValue())){
            showAlert(Alert.AlertType.ERROR, "Erreur. La date de fin du tournoi est antérieure à la date de début du tournoi.");

        }
        else{
            String s = inputNomTournoi.getText();
            champTournoi.setText(s);


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<GrandPrix,String>("name"));
        pays.setCellValueFactory(new PropertyValueFactory<GrandPrix,String>("pays"));
        date.setCellValueFactory(new PropertyValueFactory<GrandPrix,LocalDate>("date"));
    }

    @FXML
    void submit (ActionEvent event){
        if ( (nomInput.getText() == null) || (paysInput.getText() == null) || (dateInput.getValue() == null ) ){
            showAlert(Alert.AlertType.ERROR, "Tous  les champs sont obligatoires");
            return;
        }
        GrandPrix gp =new GrandPrix(nomInput.getText(),paysInput.getText(), dateInput.getValue());

        ObservableList<GrandPrix> _listesGrandPrix = tableView.getItems();
        _listesGrandPrix.add(gp);

        tableView.setItems(_listesGrandPrix);
        nomInput.setText("");
        paysInput.setText("");
        dateInput.setValue(null);

        if(_lstGrandPrix == null)
            _lstGrandPrix = new ArrayList<>();

        _lstGrandPrix.add(gp);
    }

    @FXML
    void removeGrandPrix (ActionEvent event ){
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }

    public void switchToCalendrier(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendrier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEcurie(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ecurie.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);

        tabEcurieController ecurieController = fxmlLoader.getController();
        ecurieController.setTournoi(_tournoi);

        Tournoi actuel = Tournoi.GetTournoiByCode(_tournoi.codeTournoi);
        if(actuel != null) {
            // si la liste des GP est vide, on initialise
            if (actuel.tabGrandPrix == null)
                actuel.tabGrandPrix = new ArrayList<>();

            actuel.tabGrandPrix.addAll(_lstGrandPrix);
            ArrayList<Tournoi> tournois = Tournoi.SetTournoiInList(actuel);
            System.out.println(tournois.size());
            Tournoi.WriteData(tournois);
        }

        stage.show();
    }

    public void setTournoi(Tournoi tournoi)
    {
        _tournoi = tournoi;
    }
}
