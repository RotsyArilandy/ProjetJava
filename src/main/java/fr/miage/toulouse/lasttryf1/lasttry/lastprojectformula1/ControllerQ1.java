package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.scene.control.TextField;

public class ControllerQ1 {
    private Stage stage;
    private Scene scene;

    @FXML
    private VBox container;

    private Tournoi _tournoi;
    public void switchToQ2(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    /**public static long convertToLong(TextField textField) {
        String inputText = textField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(inputText, formatter);
        Instant instant = localDateTime.toInstant(java.time.ZoneOffset.UTC);
        return instant.getEpochSecond();
    }**/
        public void setTournoi(Tournoi tournoi)
    {
            _tournoi = tournoi;

            ArrayList<Pilote> _pilote = new ArrayList<>();
            ArrayList<Object> labels = new ArrayList<>();
            ArrayList<Object> champs = new ArrayList<>();
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
