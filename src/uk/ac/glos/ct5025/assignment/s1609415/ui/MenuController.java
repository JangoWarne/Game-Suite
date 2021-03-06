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
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;

import java.io.IOException;

/**
 * This class is the controller for menu.fxml
 * it sets the handles for UI events/actions
 *
 * @author  Joshua Walker
 * @version 1.0
 */
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
            PlayersController controller = loader.getController();
            controller.setGame( game );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void oxButtonHandle(ActionEvent event) {
        // create Game
        DrawUI drawClass = new DrawUI();
        OXGame game = new OXGame( drawClass );

        try {
            Scene scene = oxButton.getScene();
            FXMLLoader loader = new FXMLLoader( getClass().getResource("players.fxml" ) );
            Pane pane = (Pane) loader.load();
            scene.setRoot(pane);

            // Add game to controller
            PlayersController controller = loader.getController();
            controller.setGame( game );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void slButtonHandle(ActionEvent event) {
        // create Game
        DrawUI drawClass = new DrawUI();
        SLGame game = new SLGame( drawClass );

        try {
            Scene scene = slButton.getScene();
            FXMLLoader loader = new FXMLLoader( getClass().getResource("players.fxml" ) );
            Pane pane = (Pane) loader.load();
            scene.setRoot(pane);

            // Add game to controller
            PlayersController controller = loader.getController();
            controller.setGame( game );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
