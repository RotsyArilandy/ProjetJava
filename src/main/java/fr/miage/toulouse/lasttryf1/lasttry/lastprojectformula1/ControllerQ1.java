package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

    public void setTournoi(Tournoi tournoi)
    {
        _tournoi = tournoi;
        //Créer un arraylist de pilote
        ArrayList<Pilote> _pilote = new ArrayList<>();
        ArrayList<Object> labels = new ArrayList<>();
        for( int i = 0; i < _tournoi.ecuries.size(); i++){
            _pilote.add(_tournoi.ecuries.get(i).getPilote1());
            _pilote.add(_tournoi.ecuries.get(i).getPilote2());
        }
        for (int i = 0; i < _pilote.size(); i++) {
            Label label = new Label("Label "+i);
            labels.add(label);
            container.getChildren().add(label);
            label.setText(_pilote.get(i).getNomPilote());
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
