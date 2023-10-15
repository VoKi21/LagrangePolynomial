module ru.vsu.cs.kvp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cgvsu.protocurvefxapp to javafx.fxml;
    exports com.cgvsu.protocurvefxapp;
}