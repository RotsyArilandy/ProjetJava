package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TournoiController  implements Initializable {

    @FXML
    private TableView<Tournoi> tab;

    @FXML
    private TableColumn<Tournoi,String> nomTournoi;

    @FXML
    private TableColumn<Tournoi,String> code;

    @FXML
    private TableColumn<Tournoi, LocalDate> dateDeb;

    @FXML
    private TableColumn<Tournoi,LocalDate> dateFin;

    @FXML
    private TableColumn<Tournoi,String> resultat;


    private ArrayList<Tournoi> _tournoi = new ArrayList<>();
    public void switchToAccueil(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Accueil.fxml"));
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //code.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("codeTournoi"));
        nomTournoi.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nomTournoi"));
        dateDeb.setCellValueFactory(new PropertyValueFactory<Tournoi,LocalDate>("DateDeb"));
        dateFin.setCellValueFactory(new PropertyValueFactory<Tournoi, LocalDate>("DateFin"));
        //resultat.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("resultat"));

    }
    public void setTournoi(ArrayList tournoi) {
        _tournoi = tournoi;

        ObservableList<Tournoi> obTournoi = tab.getItems();
        for (int i = 0 ; i < _tournoi.size(); i++){
            obTournoi.add(_tournoi.get(i));
        }
        tab.setItems(obTournoi);
    }
}
