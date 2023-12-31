module com.example.plmcrs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.plmcrs to javafx.fxml;
    exports com.example.plmcrs;
    exports com.example.plmcrs.controllers;
    opens com.example.plmcrs.controllers to javafx.fxml;
    exports com.example.plmcrs.models;
    opens com.example.plmcrs.models to javafx.fxml;
}