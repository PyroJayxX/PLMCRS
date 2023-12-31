package com.example.plmcrs.controllers;

import com.example.plmcrs.Mainscreen;
import com.example.plmcrs.models.userModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {

    @FXML
    private Label lblID;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblBirthdate;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblEmployeeID;
    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblGender;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblName;
    @FXML
    private Label lblStatus;

    @FXML
    void logout(ActionEvent event) throws IOException {
        userModel.logOutUser();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblName.setText(userModel.strName);
        lblEmail.setText(userModel.strEmail);
        lblFirstName.setText(userModel.strFirstName);
        lblLastName.setText(userModel.strLastName);
        lblGender.setText(userModel.strGender);
        lblBirthdate.setText(userModel.strBirthdate);
        lblEmployeeID.setText(userModel.strID);
        lblAddress.setText(userModel.strAddress);
        lblContact.setText(userModel.strContact);
        lblStatus.setText(userModel.strStatus);

        lblDate.setText("Today is  " + userModel.strDate);
        lblID.setText(userModel.strID);
    }

    public void toStdntList(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminStdntList.fxml","Student List");
    }

    public void toSchedApproval(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminSchedApproval.fxml","Schedule Approval");
    }

    public void toDBEditor(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminDBEditor.fxml","Database Editor");
    }
}
