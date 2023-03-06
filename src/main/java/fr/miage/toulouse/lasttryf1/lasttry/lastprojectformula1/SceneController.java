package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Label dateDeb;

    @FXML
    private Label dateFin;

    @FXML
    private Label pays;

    @FXML
    private DatePicker dateD;

    @FXML
    private DatePicker dateF;

    @FXML
    private ComboBox choixPays;

    @FXML
    private Button btn ;

    @FXML
    private Button btn2 ;

    @FXML
    private Pane panelCalendrier;

    @FXML
    private Label nomTournoi;

    @FXML
    private Label titreNomTournoi;

    @FXML
    private TextField insertNomTournoi;

    @FXML
    private SplitPane menu;

    @FXML
    private Pane panelEcurie;

    @FXML
    private Pane panelFormat;

    public void switchToScene1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 560, 560);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickValider(){
        String s= insertNomTournoi.getText();
        insertNomTournoi.setVisible(false);
        nomTournoi.setText(s);
        menu.setVisible(true);
    }



    public void onClickAjouterGP(){
        pays.setVisible(true);
        dateDeb.setVisible(true);
        dateFin.setVisible(true);
        choixPays.setVisible(true);
        dateD.setVisible(true);
        dateF.setVisible(true);
        btn.setVisible(true);
    }

    public void OnClickCalendrier(){
        panelCalendrier.setVisible(true);
        panelEcurie.setVisible(false);
        panelFormat.setVisible(false);
    }

    public void OnClickEcurie(){
        panelCalendrier.setVisible(false);
        panelEcurie.setVisible(false);
        panelFormat.setVisible(true);
    }

    public void OnClickFormat(){
        panelCalendrier.setVisible(false);
        panelFormat.setVisible(true);
        panelEcurie.setVisible(false);
    }



}
