package com.example.plmcrs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminSchedApprovalController implements Initializable {
    @FXML
    Label lblID;
    @FXML
    Label lblDate;
    @FXML
    TableView<ObservableList<String>> tblView;
    @FXML
    Label lblBack;
    @FXML
    TextField txtStudentNo;
    @FXML
    Label lblPrompt;
    @FXML
    Button btnApprove;
    @FXML
    Button btnDecline;


    public void toAdminDashboard(MouseEvent event) throws IOException {
        Mainscreen.changeScene("adminDashboard.fxml","Admin Dashboard");
    }



    public void toStdntList(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminStdntList.fxml","Student List");
    }


    public void toDBEditor(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminDBEditor.fxml","Database Editor");
    }

    public void approveSched(ActionEvent event) {

    }

    public void declineSched(ActionEvent event) {

    }

    public void tblClick(MouseEvent event){
        txtStudentNo.setText(tblView.getSelectionModel().getSelectedItem().get(1));
        userModel.strIDFormat = txtStudentNo.getText();
    }

    public void searchStudent(ActionEvent event) throws SQLException {
        userModel.strIDFormat = txtStudentNo.getText();
        tableModel.getApprvTbl();
        tableModel.resultSetToTableView(tableModel.tblrs, tblView);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText("Today is  " + userModel.strDate);
        lblID.setText(userModel.strID);
        lblBack.setVisible(false);
        btnApprove.setDisable(true);
        btnDecline.setDisable(true);
        try {
            tableModel.getApprvTblStdntList();
            tableModel.resultSetToTableView(tableModel.tblrs, tblView);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
