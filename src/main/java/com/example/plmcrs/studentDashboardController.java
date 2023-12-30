package com.example.plmcrs;

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

    public void toGrades(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentGrade.fxml","Grades");
    }
    public void toRegister(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentRegister.fxml","Register subjects");
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
    }
}
