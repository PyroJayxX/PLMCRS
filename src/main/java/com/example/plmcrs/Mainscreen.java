package com.example.plmcrs;

import com.example.plmcrs.models.userModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Mainscreen extends Application {

    public static Stage window;
    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
        public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader(Mainscreen.class.getResource("login.fxml"));
                Scene scene = new Scene(loader.load());
                window = stage;
                window.setScene(scene);
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                System.out.print(userModel.currentYear);
    }

        public static void changeScene(String fxmlFileName, String windowTitle) throws IOException {
            FXMLLoader loader = new FXMLLoader(Mainscreen.class.getResource(fxmlFileName));
            scene = new Scene(loader.load());
            window.setTitle(windowTitle);
            window.setScene(scene);
        }

        public static void popDialogue(String message, String windowTitle, String header) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(windowTitle);
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

