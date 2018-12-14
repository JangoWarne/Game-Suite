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
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * This class is the controller for oxSize.fxml
 * it sets the handles for UI events/actions
 * it is used to select the noughts and crosses game board size
 * it loads the oxGame.fxml when the user selects done
 * it loads the menu.fxml scene if user selects to go back
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public class OXSizeController {

    private OXGame game;

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

    public void setGame(OXGame game) {
        this.game = game;
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
        // get board size
        int boardSize = parseInt(lengthChoiceBox.getValue().toString());
        int winLength = 3;
        if (boardSize > 3) {
            winLength = 4;
        }
        game.setEdgeSize(boardSize);
        game.setWinLength(winLength);

        try {
            // Change Scene
            Scene scene = doneButton.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("oxGame.fxml"));
            Pane pane = (Pane) loader.load();
            scene.setRoot(pane);

            // Add game to controller
            OXGameController controller = loader.getController();
            controller.setGame(game);

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
