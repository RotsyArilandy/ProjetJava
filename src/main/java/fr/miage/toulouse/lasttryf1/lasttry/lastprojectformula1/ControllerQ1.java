package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerQ1 {
    private Stage stage;
    private Scene scene;

    public void switchToQ2(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Q2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
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
