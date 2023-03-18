package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.util.Arrays;
public class TrierLesElements {
    TableView<Temps> tableView = new TableView<Temps>();
    ObservableList<Temps> items = tableView.getItems();

    Arrays.sort(Temps);
    public void Campare(){
        for (int i = 0; i < Temps.length; i++) {
            System.out.println(Temps[i]);
        }
    }
}
