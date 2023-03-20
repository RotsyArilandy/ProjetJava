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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.CharBuffer;
import java.time.LocalDate;
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

    private ArrayList<GrandPrix> _grandPrix;
    private static final String _dataPath = "data/ecurie.json";


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
        GrandPrix GP = new GrandPrix(nomInput.getText(),paysInput.getText(), dateInput.getValue());
        ObservableList<GrandPrix> X = tableView.getItems();
        X.add(GP);

        tableView.setItems(X);
        nomInput.setText("");
        paysInput.setText("");
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
        stage.show();
    }

    public void setGP(ActionEvent event )
    {
        BufferedReader reader = null;
        String json = "";

        try
        {
            _grandPrix = new ArrayList<>();
            reader = new BufferedReader(new FileReader(_dataPath));
            String line;
            while ((line = reader.readLine()) != null)
                json += line;

            JSONArray data = new JSONArray(json);
            for(int i=0; i < data.length(); i++)
            {
                JSONObject object = data.getJSONObject(i);
                _grandPrix.add(new GrandPrix(
                        object.getString("name"),
                        object.getString("pays"),
                        LocalDate.parse(object.getString("date"))
                ));
            }
            setGrandPrixTableView();

        } catch (Exception e) {
            // alert "erreur sur la lecture du fichier
            System.out.println("erreur sur la lecture du fichier" + e.getMessage());
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void setGrandPrixTableView()  {
        ObservableList <GrandPrix> obGrandPrix = tableView.getItems();

        for (GrandPrix gp: _grandPrix)
            obGrandPrix.add(gp);

        tableView.setItems(obGrandPrix);
    }
    void save (ActionEvent event ){
        String [] data = new String[_grandPrix.size()];
        for (int i = 0; i < data.length; i++) {
            GrandPrix gp = _grandPrix.get(i);
            JSONObject obj = new JSONObject(gp);
            data[i] = obj.toString();
        }
        try {
            String finalString = "[";
            finalString += String.join(",", data);
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
}