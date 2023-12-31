package com.example.plmcrs.controllers;

import com.example.plmcrs.Mainscreen;
import com.example.plmcrs.models.tableModel;
import com.example.plmcrs.models.userModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentGradeController implements Initializable {

    @FXML
    Label lblStdntNum;
    @FXML
    TableView<ObservableList<String>> gradeTblView;
    @FXML
    private ComboBox<String> cmbGrade;
    @FXML
    void logout(ActionEvent event) throws IOException {
        userModel.logOutUser();
    }

    public void toStdntDashboard(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentDashboard.fxml","Dashboard");
    }

    public void toSchedule(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentSchedule.fxml", "Schedule");
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

    public void cmbGradeSelect(ActionEvent event) throws SQLException {
            tableModel.tblSem = String.valueOf(cmbGrade.getValue().charAt(10));
            tableModel.tblSY = cmbGrade.getValue().substring(0,9);
            tableModel.getStdntGrade();
        if (!tableModel.tblrs.isBeforeFirst()) {
            Mainscreen.popDialogue("No grades to display yet.", "Alert!", "Grades not found.");
        } else {
            try {
                tableModel.resultSetToTableView(tableModel.tblrs, gradeTblView);
                } catch (SQLException e) {
                e.printStackTrace();
                }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblStdntNum.setText(userModel.strIDFormat);
        cmbGrade.setItems(FXCollections.observableArrayList("2021-2022 1ST SEMESTER", "2021-2022 2ND SEMESTER", "2022-2023 1ST SEMESTER", "2022-2023 2ND SEMESTER", "2023-2024 1ST SEMESTER", "2023-2024 2ND SEMESTER"));
    }
}
