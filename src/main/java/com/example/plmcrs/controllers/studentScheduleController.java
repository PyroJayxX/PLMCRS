package com.example.plmcrs.controllers;

import com.example.plmcrs.Mainscreen;
import com.example.plmcrs.models.tableModel;
import com.example.plmcrs.models.userModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentScheduleController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tblView;
    @FXML
    Button btnRegister;
    @FXML
    Button btnRemove;
    @FXML
    private Label lblSched;
    @FXML
    private Label lblStdntNum;
    @FXML
    void logout(ActionEvent event) throws IOException {
        userModel.logOutUser();
    }
    public void toStdntDashboard(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentDashboard.fxml","Dashboard");
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


    @FXML
    void registerSched(ActionEvent event) throws SQLException {
        tableModel.addToApprvTbl();
    }

    @FXML
    void removeSched(ActionEvent event) throws SQLException {
        tableModel.strSubjCode = tblView.getSelectionModel().getSelectedItem().get(0);
        tableModel.strSubjName = tblView.getSelectionModel().getSelectedItem().get(1);
        tableModel.strDay = tblView.getSelectionModel().getSelectedItem().get(2);
        tableModel.strTime = tblView.getSelectionModel().getSelectedItem().get(3);
        tableModel.strRoom = tblView.getSelectionModel().getSelectedItem().get(4);
        tableModel.strType = tblView.getSelectionModel().getSelectedItem().get(5);
        tableModel.removeFromSched();
        tableModel.getRegTbl();
        tableModel.resultSetToTableView(tableModel.tblrs, tblView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblStdntNum.setText(userModel.strIDFormat);

        switch (userModel.strStdntType) {
            case "Regular":
                try {
                    lblSched.setText("Your Schedule");
                    tableModel.getSchedule();
                    tableModel.resultSetToTableView(tableModel.tblrs, tblView);
                    btnRegister.setDisable(true);
                    btnRemove.setDisable(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Irregular":
                try {
                    if (userModel.hasExistingSchedule()) {
                        tableModel.getIrregSchedule();
                        tableModel.resultSetToTableView(tableModel.tblrs, tblView);
                    } else {
                        tableModel.getRegTbl();
                        tableModel.resultSetToTableView(tableModel.tblrs, tblView);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
