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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerQ1 implements Initializable{

    private Stage stage;
    private Scene scene;

    public TableView<Pilote> tableView;

    @FXML
    public TableColumn<Pilote,String> pilote;

    @FXML
    public  TableColumn<Pilote,Long> temps;

    @FXML
    private VBox container;
    static ArrayList<Pilote> _pilote = new ArrayList<>();
    ArrayList<Object> labels = new ArrayList<>();
    ArrayList<TextField> champs = new ArrayList<>();

    private Tournoi _tournoi;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        pilote.setCellValueFactory(new PropertyValueFactory<Pilote,String>("nomPilote"));

    }

    /**
     *
     * @param event
     * @throws IOException
     */
    public void switchToQ2(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }


        public void setTournoi(Tournoi tournoi)
    {
            _tournoi = tournoi;

            //Créer un arraylist de pilote
            for( int i = 0; i < _tournoi.ecuries.size(); i++){
                _pilote.add(_tournoi.ecuries.get(i).getPilote1());
                _pilote.add(_tournoi.ecuries.get(i).getPilote2());
            }
            for (int i = 0; i < _pilote.size(); i++) {
                Label label = new Label("Label "+i);
                TextField champ = new TextField();
                labels.add(label);
                champs.add(champ);
                container.getChildren().add(label);
                container.getChildren().add(champ);
                label.setText(_pilote.get(i).getNomPilote());

        }
    }
    /**public static long convertToLong(TextField textField) {
        String inputText = textField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(inputText, formatter);
        Instant instant = localDateTime.toInstant(java.time.ZoneOffset.UTC);
        return instant.getEpochSecond();
    }**/

        public void trier() {
            //lien entre le textfiled et les pilote
            for (int i = 0; i < _pilote.size(); i++) {
                _pilote.get(i).temps = parseLong(champs.get(i));
            }
            Collections.sort(_pilote);
            ObservableList<Pilote> obEcurie = tableView.getItems();
            obEcurie.clear();
            for (int i = 0; i < _pilote.size(); i++) {
                obEcurie.add(_pilote.get(i));
            }
            tableView.setItems(obEcurie);
        }





        public static Long parseLong(TextField textField) {
            String inputText = textField.getText();
            try {
                return Long.parseLong(inputText);
            } catch (NumberFormatException e) {
                return null; // renvoie null si le texte ne peut pas être analysé en Long
            }

    }


}
    /**
    private int[] tab;
    private int temp;


    public void tableauQ1(){
        tab = new int[20];
    }
    //public void trier(){
        //while(boutonTrier==true)
           // for(int i=0;i<tab.length;i++){
                //if(tab[i]>tab[i+1]){
                  //  temp=tab[i];
                  //  tab[i]=tab[i+1];
                  //  tab[i+1]=temp;
             //   }
           // }
   // }


    public void switchToECURIE(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ecurie.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }
}**/
