module com.example.cm1601_cw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.cm1601_cw to javafx.fxml;
    exports com.example.cm1601_cw;
    exports FX;
    opens FX to javafx.fxml;
}