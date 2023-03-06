package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class tabEcurieController implements Initializable {
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

    int nbClick = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ecurie.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("ecurie"));
        pilote1.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("pilote1"));
        pilote2.setCellValueFactory(new PropertyValueFactory<Ecurie,String>("pilote2"));
    }

    @FXML
    void submit (ActionEvent event){
        if (nbClick < 10){
            Ecurie E = new Ecurie(ecurieInput.getText(),(Pilote)(pilote1Input.getText()), pilote2Input.getText());
            ObservableList <Ecurie> obEcurie= tableView.getItems();
            obEcurie.add(E);
            tableView.setItems(obEcurie);
            nbClick++;

        }
    }
    @FXML
    void removeGrandPrix (ActionEvent event ){
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }


}
