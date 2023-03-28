package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void switchToCalendrier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendrier.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAccueil(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Accueil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    //A VERIFIER ET A TESTER
    public void switchToAjouterGP(ActionEvent event) throws IOException {
        if ((nomTournoi.getText() == null) || (DateDeb.getValue() == null) || (DateFin.getValue() == null)) {
            showAlert(Alert.AlertType.ERROR, "Tous  les champs sont obligatoires");
            return;
        }
        if (DateDeb.getValue().isAfter(DateFin.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Erreur !La date de fin du tournoi est antérieure à la date de début du tour");
        }
        if (nomTournoi.getText() == "") {
            showAlert(Alert.AlertType.ERROR, "Le nom du tournoi est obligatoire");
        } else if (DateDeb.getValue().isAfter(DateFin.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Erreur !La date de fin du tournoi est antérieure à la date de début du tournoi.");
        } else {
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

    public void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                alert.close();
            }
        });
    }

    public void accederAutresTournoi() {
        ArrayList<Tournoi> _tournoisExistants = Tournoi.GetTournoisFromList();
        ObservableList<Tournoi> _listesGrandPrix = this.tableViewList.getItems();
        for (Tournoi tour : _tournoisExistants) {
            _listesGrandPrix.add(tour);
        }
        this.tableViewList.setItems(_listesGrandPrix);
        this.tableViewList.setVisible(true);
    }

    /**
     * Permet de charger la vue tournoi.fxml
     *
     * @param event
     * @throws IOException
     */
    public void switchToTournoi(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tournoi.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        //charger les tournois de tournoi.json vers l arrayliyt de tournoi
        _tournoi = GetTournoisFromList(); //récupère les tournois dans tournoi.json
        TournoiController controllerScene = fxmlLoader.getController(); // je récupère le controlleurScene
        controllerScene.setTournoi(_tournoi);
        stage.setScene(scene);
        stage.show();


    }
    public void setTournoi(ArrayList tournoi) {
        _tournoi = tournoi;
    }

    public static ArrayList<Tournoi> GetTournoisFromList() {
        // récupérer tout ce qui est dans tournois.json
        BufferedReader reader = null;
        String json = "";
        ArrayList<Tournoi> tournois = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("data/tournoi.json"));
            String line;
            while ((line = reader.readLine()) != null)
                json += line;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // [{"nomTournoi":"tournoi12","codeTournoi":"TOU001","dateDeb":"2023-03-22","tabGrandPrix":[],"ecuries":[],"dateFin":"2023-03-24"}]
            // new JSONArray("[{"nomTournoi":"tournoi12","codeTournoi":"TOU001","dateDeb":"2023-03-22","tabGrandPrix":[],"ecuries":[],"dateFin":"2023-03-24"}]")
            JSONArray data = new JSONArray(json);
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                Tournoi tour = new Tournoi(
                        object.getString("nomTournoi"),
                        object.getString("codeTournoi"),
                        LocalDate.parse(object.getString("dateDeb"), formatter),
                        LocalDate.parse(object.getString("dateFin"), formatter)
                        // il manque la gestion des arrays Ecuries
                        // attention c'est un array. c'est pas des objets
                );
                JSONArray listGrandPrixSousFormeArray = object.getJSONArray("tabGrandPrix");
                for (int j = 0; j < listGrandPrixSousFormeArray.length(); j++) {
                    JSONObject gpObject = listGrandPrixSousFormeArray.getJSONObject(j);
                    GrandPrix gp = new GrandPrix(
                            gpObject.getString("name"),
                            gpObject.getString("pays"),
                            null // à modifier
                            // LocalDate.parse(object.getString("date"), formatter)
                    );
                    tour.tabGrandPrix.add(gp);
                }
                JSONArray listEcuriesSousFormeArray = object.getJSONArray("ecuries");
                for (int j = 0; j < listEcuriesSousFormeArray.length(); j++) {
                    JSONObject gpObject = listEcuriesSousFormeArray.getJSONObject(j);
                    JSONObject P1 = (JSONObject) gpObject.get("pilote1");
                    JSONObject P2 = (JSONObject)gpObject.get("pilote2");
                    Pilote p1 = new Pilote(P1.getString("nomPilote"));
                    Pilote p2 = new Pilote(P2.getString("nomPilote"));
                    Ecurie ecurie = new Ecurie(
                            gpObject.getString("ecurie"),p1, p2
                    );
                    tour.ecuries.add(ecurie);
                }
                tournois.add(tour);
                int a= 1;
            }
        } catch (Exception e) {
            // alert "erreur sur la lecture du fichier
            System.out.println("erreur sur la lecture du fichier" + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tournois;



    }
}


