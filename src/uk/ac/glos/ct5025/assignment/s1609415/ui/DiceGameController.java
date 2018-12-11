package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.io.IOException;


public class DiceGameController {

    @FXML
    private Region backRegion;


    @FXML
    private void initialize() {
        // Handle Button event.
        backRegion.setOnMouseClicked(this::backRegionHandle);
    }

    private void backRegionHandle(MouseEvent event) {
        try {
            Scene scene = backRegion.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("menu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
