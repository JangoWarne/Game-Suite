package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;

import java.io.IOException;


public class OXGameController {

    private Game game;

    @FXML
    private Region backRegion;
    @FXML
    private GridPane boardGridPane;
    @FXML
    private TilePane boardTilePane;
    @FXML
    private TextArea winTextArea;


    @FXML
    private void initialize() {
        winTextArea.setVisible(false);

        // Handle Button event.
        backRegion.setOnMouseClicked(this::backRegionHandle);
    }

    public void setGame(Game game) {
        // Set DrawUI scene
        game.getDrawClass().setScene( game, backRegion.getScene(), winTextArea, boardTilePane );

        // Start Game
        this.game = game;
        game.startGame();
    }

    private void backRegionHandle(MouseEvent event) {
        // go back to main menu
        try {
            Scene scene = backRegion.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("menu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
