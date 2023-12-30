package com.example.plmcrs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class studentDashboardController implements Initializable {

    @FXML
    Label lblName;
    @FXML
    Label lblFirstName;
    @FXML
    Label lblLastName;
    @FXML
    Label lblAge;
    @FXML
    Label lblGender;
    @FXML
    Label lblContact;
    @FXML
    Label lblBirthdate;
    @FXML
    Label lblStdntNo;
    @FXML
    Label lblStdntNum;
    @FXML
    Label lblCourse;
    @FXML
    Label lblEmail;
    @FXML
    Label lblAddress;
    @FXML
    Label lblSY;
    @FXML
    Label lblDate;
    @FXML
    Label lblStdntType;
    @FXML
    void logout(ActionEvent event) throws IOException {
        userModel.logOutUser();
    }


    public void toGrades(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentGrade.fxml","Grades");
    }
    public void toRegister(MouseEvent event) throws IOException {
        switch(userModel.strStdntType) {
            case "Regular":
                Mainscreen.popDialogue("You are already enrolled!", "Error.", "Cannot register right now.");
                break;
            case "Irregular":
                Mainscreen.changeScene("studentRegister.fxml","Register subjects");
                break;
        }
    }
    public void toSchedule(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentSchedule.fxml", "Schedule");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblName.setText(userModel.strName);
        lblEmail.setText(userModel.strEmail);
        lblFirstName.setText(userModel.strFirstName);
        lblLastName.setText(userModel.strLastName);
        lblAge.setText(userModel.strAge);
        lblGender.setText(userModel.strGender);
        lblContact.setText(userModel.strContact);
        lblBirthdate.setText(userModel.strBirthdate);
        lblStdntNo.setText(userModel.strIDFormat);
        lblStdntNum.setText(userModel.strIDFormat);
        lblAddress.setText(userModel.strAddress);
        lblCourse.setText(userModel.strCourse);
        lblSY.setText(userModel.strSY);
        lblDate.setText("Today is  " + userModel.strDate);
        lblStdntType.setText(userModel.strStdntType);
    }
}
