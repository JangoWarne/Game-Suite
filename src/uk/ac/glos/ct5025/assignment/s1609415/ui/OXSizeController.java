package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;


public class OXSizeController {

    @FXML
    private Region backRegion;
    @FXML
    private Button doneButton;
    @FXML
    private ChoiceBox lengthChoiceBox;
    @FXML
    private Label xAxisLabel;
    @FXML
    private Label yAxisLabel;

    @FXML
    private void initialize() {
        // Handle Button event.
        backRegion.setOnMouseClicked(this::backRegionHandle);
        doneButton.setOnAction(this::doneButtonHandle);
        lengthChoiceBox.setOnAction(this::lengthChoiceBoxHandle);

        xAxisLabel.setText("3");
        yAxisLabel.setText("3");
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
            scene.setRoot(FXMLLoader.load(getClass().getResource("oxGame.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void lengthChoiceBoxHandle(Event event) {

        String text = lengthChoiceBox.getValue().toString();
        xAxisLabel.setText( text );
        yAxisLabel.setText( text );
    }
}
