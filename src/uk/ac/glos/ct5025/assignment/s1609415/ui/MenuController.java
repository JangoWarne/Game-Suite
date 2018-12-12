package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import uk.ac.glos.ct5025.assignment.s1609415.game.DiceGame;

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
        // create Game
        DrawUI drawClass = new DrawUI();
        DiceGame game = new DiceGame( drawClass );

        try {
            // Change Scene
            Scene scene = diceButton.getScene();
            FXMLLoader loader = new FXMLLoader( getClass().getResource("players.fxml" ) );
            Pane pane = (Pane) loader.load();
            scene.setRoot(pane);

            // Add game to controller
            PlayersController controller = loader.<PlayersController>getController();
            controller.setGame( game );
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
