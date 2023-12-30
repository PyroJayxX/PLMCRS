package com.example.plmcrs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class adminDBEditorController {
    @FXML
    Label lblID;
    @FXML
    Label lblDate;

    public void toAdminDashboard(MouseEvent event) throws IOException {
        Mainscreen.changeScene("adminDashboard.fxml","Admin Dashboard");
    }


    public void toStdntList(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminStdntList.fxml","Student List");
    }

    public void toSchedApproval(ActionEvent event) throws IOException {
        Mainscreen.changeScene("adminSchedApproval.fxml","Schedule Approval");
    }

    public void delete(ActionEvent event) {

    }


    public void add(ActionEvent event) {

    }

    public void update(ActionEvent event) {
    }
}
