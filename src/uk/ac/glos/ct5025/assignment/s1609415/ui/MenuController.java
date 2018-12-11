package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import java.io.IOException;


public class MenuController {

    @FXML
    private Button diceButton;
    @FXML
    private Button oxButton;
    @FXML
    private Button slButton;


    @FXML
    private void initialize() {
        // Handle Button event.
        diceButton.setOnAction(this::diceButtonHandle);
        oxButton.setOnAction(this::oxButtonHandle);
        slButton.setOnAction(this::slButtonHandle);
    }

    private void diceButtonHandle(ActionEvent event) {
        try {
            Scene scene = diceButton.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("players.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void oxButtonHandle(ActionEvent event) {
        try {
            Scene scene = diceButton.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("players.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void slButtonHandle(ActionEvent event) {
        try {
            Scene scene = diceButton.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("players.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
