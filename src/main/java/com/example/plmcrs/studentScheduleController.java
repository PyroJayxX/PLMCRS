package com.example.plmcrs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentScheduleController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> approvalTblView;
    @FXML
    private Label lblSched;
    @FXML
    private Label lblStdntNum;

    public void toStdntDashboard(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentDashboard.fxml","Dashboard");
    }
    public void toRegister(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentRegister.fxml","Register subjects");
    }
    public void toGrades(MouseEvent event) throws IOException {
        Mainscreen.changeScene("studentGrade.fxml","Grades");
    }


    @FXML
    void registerSched(ActionEvent event) {

    }

    @FXML
    void removeSched(ActionEvent event) throws SQLException {
        tableModel.strSubjCode = approvalTblView.getSelectionModel().getSelectedItem().get(0);
        tableModel.strSubjName = approvalTblView.getSelectionModel().getSelectedItem().get(1);
        tableModel.strDay = approvalTblView.getSelectionModel().getSelectedItem().get(2);
        tableModel.strTime = approvalTblView.getSelectionModel().getSelectedItem().get(3);
        tableModel.strRoom = approvalTblView.getSelectionModel().getSelectedItem().get(4);
        tableModel.strType = approvalTblView.getSelectionModel().getSelectedItem().get(5);
        tableModel.removeFromSched();
        tableModel.getApprvTbl();
        tableModel.resultSetToTableView(tableModel.tblrs, approvalTblView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblStdntNum.setText(userModel.strIDFormat);
        try {
            tableModel.getApprvTbl();
            tableModel.resultSetToTableView(tableModel.tblrs, approvalTblView);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
