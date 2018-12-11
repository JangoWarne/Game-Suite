package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;


public class PlayersController {

    @FXML
    private Region backRegion;
    @FXML
    private Button doneButton;
    @FXML
    private ChoiceBox p1ChoiceBox;
    @FXML
    private ChoiceBox p2ChoiceBox;
    @FXML
    private Pane human1Pane;
    @FXML
    private Pane computer1Pane;
    @FXML
    private Pane human2Pane;
    @FXML
    private Pane computer2Pane;

    @FXML
    private void initialize() {
        // Handle Button event.
        backRegion.setOnMouseClicked(this::backRegionHandle);
        doneButton.setOnAction(this::doneButtonHandle);
        p1ChoiceBox.setOnAction(this::p1ChoiceBoxHandle);
        p2ChoiceBox.setOnAction(this::p2ChoiceBoxHandle);

        computer1Pane.setVisible(false);
        human2Pane.setVisible(false);
    }

    private void backRegionHandle(MouseEvent event) {
        try {
            Scene scene = backRegion.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("menu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doneButtonHandle(ActionEvent event) {
        try {
            Scene scene = doneButton.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("oxSize.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void p1ChoiceBoxHandle(Event event) {

        if(p1ChoiceBox.getValue().toString().equals("Human")) {
            computer1Pane.setVisible(false);
            human1Pane.setVisible(true);
        } else {
            human1Pane.setVisible(false);
            computer1Pane.setVisible(true);
        }
    }

    private void p2ChoiceBoxHandle(Event event) {

        if(p2ChoiceBox.getValue().toString().equals("Human")) {
            computer2Pane.setVisible(false);
            human2Pane.setVisible(true);
        } else {
            human2Pane.setVisible(false);
            computer2Pane.setVisible(true);
        }
    }
}
