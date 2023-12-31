package com.example.plmcrs.controllers;

import com.example.plmcrs.models.userModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void loginButton(ActionEvent event) throws IOException, SQLException {
        // Getters
        userModel.strUsername = usernameField.getText();
        userModel.strPassword = passwordField.getText();
        userModel.strID = usernameField.getText();
        //Formats the strID to strIDFormat where 202200000 => 2022-00000
        userModel.strIDFormat = userModel.strID.substring(0, 4) + "-" + userModel.strID.substring(4);

        userModel.logInUser();

    }
}







