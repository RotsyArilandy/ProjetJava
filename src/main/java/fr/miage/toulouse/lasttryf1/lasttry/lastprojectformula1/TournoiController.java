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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TournoiController {
    @FXML
    VBox code;
    @FXML
    VBox t;
    @FXML
    VBox deb;
    @FXML
    VBox fin;
    @FXML
    VBox resultat;


    private ArrayList<Tournoi> _tournoi = new ArrayList<>();

    public void switchToAccueil(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Accueil.fxml"));
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * permet de récupérer le tournoi de la page précédente
     * @param tournoi tournoi envoyé par le controlleur de la page précédente
     */
    public void setTournoi(ArrayList tournoi) {
        _tournoi = tournoi;


        for (int i = 0; i < _tournoi.size(); i++) {
            Label V1 = new Label();
            Label V2 = new Label();
            Label V3 = new Label();
            Label V4 = new Label();
            Label V5 = new Label();
            code.getChildren().add(V1);
            t.getChildren().add(V2);
            deb.getChildren().add(V3);
            fin.getChildren().add(V4);
            resultat.getChildren().add(V5);
            V1.setText(_tournoi.get(i).codeTournoi);
            V2.setText(_tournoi.get(i).nomTournoi);
            V3.setText(_tournoi.get(i).dateDebToString());
            V4.setText(_tournoi.get(i).dateFinToString());
            //V5.setText(_tournoi.get(i).resultatToString()); // Dépend de q1, q2, q3



        }


    }
}

