package com.example.plmcrs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentRegisterController implements Initializable {
    @FXML
    private Label lblStdntNum;
    @FXML
    private Label lblDate;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<ObservableList<String>> subjectTblView;

    public void toStdntDashboard(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentDashboard.fxml","Dashboard");
    }

    public void toGrades(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentGrade.fxml","Grades");
    }

    public void toSchedule(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentSchedule.fxml", "Schedule");
    }

    public void btnTblSearch(ActionEvent event) throws SQLException {
        tableModel.strSearch = txtSearch.getText() + "%";
        tableModel.getSubjectList();
        if (!tableModel.tblrs.isBeforeFirst()) {
            Mainscreen.popDialogue("Please enter a valid subject name.", "Alert!", "No results found.");
        } else {
            try {
                tableModel.resultSetToTableView(tableModel.tblrs, subjectTblView);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void txtSearchEnter(ActionEvent event) throws SQLException {
        tableModel.strSearch = txtSearch.getText() + "%";
        tableModel.getSubjectList();
        if (!tableModel.tblrs.isBeforeFirst()) {
            Mainscreen.popDialogue("Please enter a valid subject name.", "Alert!", "No results found.");
        } else {
            try {
                tableModel.resultSetToTableView(tableModel.tblrs, subjectTblView);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void tblClick(MouseEvent event) {
        txtSearch.setText(subjectTblView.getSelectionModel().getSelectedItem().get(3));
    }

    public void addSched(ActionEvent event) throws SQLException {
        tableModel.tblSY = subjectTblView.getSelectionModel().getSelectedItem().get(0);
        tableModel.tblSem = subjectTblView.getSelectionModel().getSelectedItem().get(1);
        tableModel.strBlock = subjectTblView.getSelectionModel().getSelectedItem().get(2);
        tableModel.strSubjName = subjectTblView.getSelectionModel().getSelectedItem().get(3);
        tableModel.strSubjCode = subjectTblView.getSelectionModel().getSelectedItem().get(4);
        tableModel.strDay = subjectTblView.getSelectionModel().getSelectedItem().get(5);
        tableModel.strTime = subjectTblView.getSelectionModel().getSelectedItem().get(6);
        tableModel.strRoom = subjectTblView.getSelectionModel().getSelectedItem().get(7);
        tableModel.strType = subjectTblView.getSelectionModel().getSelectedItem().get(8);
        tableModel.addToSched();
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        lblStdntNum.setText(userModel.strIDFormat);
        lblDate.setText(userModel.strDate);
        try {
            tableModel.getSubjectList();
            tableModel.resultSetToTableView(tableModel.tblrs, subjectTblView);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
