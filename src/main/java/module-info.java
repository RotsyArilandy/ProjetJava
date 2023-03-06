module fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1 to javafx.fxml;
    exports fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;
}