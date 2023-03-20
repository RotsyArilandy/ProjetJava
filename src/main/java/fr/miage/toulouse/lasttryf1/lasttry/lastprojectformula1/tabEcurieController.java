package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.collections.FXCollections;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.URL;
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
    private TextField pilote1Input;

    @FXML
    private TextField pilote2Input;

    private ArrayList<Ecurie> _ecuries;
    private static final String _dataPath = "data/ecurie.json";

    int nbClick = 0;

    public tabEcurieController()
    {
        _ecuries = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ecurie.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("ecurie"));
        pilote1.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("pilote1"));
        pilote2.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("pilote2"));
    }

    @FXML
    void submit (ActionEvent event){
        if (nbClick < 10){
            Ecurie E = new Ecurie(ecurieInput.getText(),(pilote1Input.getText()), pilote2Input.getText());
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

    @FXML
    void save (ActionEvent event ){
        String [] data = new String[_ecuries.size()];
        for (int i = 0; i < data.length; i++) {
            Ecurie e = _ecuries.get(i);
            JSONObject obj = new JSONObject(e);
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
    public void switchToQ1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTournois(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createTournoi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setEcuries(ActionEvent event )
    {
        BufferedReader reader = null;
        String json = "";

        try
        {
            _ecuries = new ArrayList<>();
            reader = new BufferedReader(new FileReader(_dataPath));
            String line;
            while ((line = reader.readLine()) != null)
                json += line;

            JSONArray data = new JSONArray(json);
            for(int i=0; i < data.length(); i++)
            {
                JSONObject object = data.getJSONObject(i);
                _ecuries.add(new Ecurie(
                        object.getString("ecurie"),
                        object.getString("pilote1"),
                        object.getString("pilote2")
                ));
            }
            setEcurieTableView();

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

    private void setEcurieTableView()  {
        ObservableList <Ecurie> obEcurie = tableView.getItems();

        for (Ecurie e: _ecuries)
            obEcurie.add(e);

        tableView.setItems(obEcurie);
    }


    public void switchQ1(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);

        stage.show();
    }


}