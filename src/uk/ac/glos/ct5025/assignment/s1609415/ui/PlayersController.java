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
import uk.ac.glos.ct5025.assignment.s1609415.game.DiceGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;

import java.io.IOException;
import java.util.ArrayList;


public class PlayersController {

    private Game game;

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
        // Handle UI events
        backRegion.setOnMouseClicked(this::backRegionHandle);
        doneButton.setOnAction(this::doneButtonHandle);
        p1ChoiceBox.setOnAction(this::p1ChoiceBoxHandle);
        p2ChoiceBox.setOnAction(this::p2ChoiceBoxHandle);

        computer1Pane.setVisible(false);
        human2Pane.setVisible(false);
    }

    public void setGame(Game game) {
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
        // Get Player Types
        Player.PlayerType player1Type;
        Player.PlayerType player2Type;

        if(p1ChoiceBox.getValue().toString().equals("Human")) {
            player1Type = Player.PlayerType.Human;
        } else {
            player1Type = Player.PlayerType.Computer;
        }

        if(p2ChoiceBox.getValue().toString().equals("Human")) {
            player2Type = Player.PlayerType.Human;
        } else {
            player2Type = Player.PlayerType.Computer;
        }

        // Based on game type
        if(game.getGameType() == Game.GameType.diceGame) {

            // Add players to game
            DicePlayer player1 = new DicePlayer(player1Type, Player.PlayerName.player1);
            DicePlayer player2 = new DicePlayer(player2Type, Player.PlayerName.player2);
            ArrayList<Player> players = new ArrayList<>();
            players.add(player1);
            players.add(player2);
            game.setPlayers(players);

            try {
                // Change Scene
                Scene scene = doneButton.getScene();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("diceGame.fxml"));
                Pane pane = (Pane) loader.load();
                scene.setRoot(pane);

                // Add game to controller
                try {
                    DiceGameController controller = loader.<DiceGameController>getController();
                    controller.setGame((DiceGame) game);

                } catch (ClassCastException e) {
                    System.out.println("DiceGame class not used for dice game");
                    System.out.println(e);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Do Nothing
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
