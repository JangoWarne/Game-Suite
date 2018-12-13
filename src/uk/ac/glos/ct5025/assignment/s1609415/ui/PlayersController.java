package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import uk.ac.glos.ct5025.assignment.s1609415.game.DiceGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.OXPlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.player.SLPlayer;

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
        Player.playerType player1Type;
        Player.playerType player2Type;

        if(p1ChoiceBox.getValue().toString().equals("Human")) {
            player1Type = Player.playerType.Human;
        } else {
            player1Type = Player.playerType.Computer;
        }

        if(p2ChoiceBox.getValue().toString().equals("Human")) {
            player2Type = Player.playerType.Human;
        } else {
            player2Type = Player.playerType.Computer;
        }

        // Based on game type
        if(game.getGameType() == Game.gameType.diceGame) {

            // Add players to game
            ArrayList<Player> players = new ArrayList<>();
            players.add( new DicePlayer(player1Type, Player.playerName.player1) );
            players.add( new DicePlayer(player2Type, Player.playerName.player2) );
            game.setPlayers(players);

            try {
                // Change Scene
                Scene scene = doneButton.getScene();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("diceGame.fxml"));
                Pane pane = (Pane) loader.load();
                scene.setRoot(pane);

                // Add game to controller
                DiceGameController controller = loader.getController();
                controller.setGameClass(game);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(game.getGameType() == Game.gameType.oxGame) {

            // Add players to game
            ArrayList<Player> players = new ArrayList<>();
            players.add( new OXPlayer( game, player1Type, Player.playerName.player1) );
            players.add( new OXPlayer( game, player2Type, Player.playerName.player2) );
            game.setPlayers(players);

            try {
                // Change Scene
                Scene scene = doneButton.getScene();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("oxSize.fxml"));
                Pane pane = (Pane) loader.load();
                scene.setRoot(pane);

                // Add game to controller
                try {
                    OXSizeController controller = loader.getController();
                    controller.setGame((OXGame) game);

                } catch (ClassCastException e) {
                    System.out.println("OXGame class not used for O&X game");
                    System.out.println(e);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(game.getGameType() == Game.gameType.slGame) {

            // Add players to game
            ArrayList<Player> players = new ArrayList<>();
            players.add( new SLPlayer(player1Type, Player.playerName.player1) );
            players.add( new SLPlayer(player2Type, Player.playerName.player2) );
            game.setPlayers(players);

            try {
                // Change Scene
                Scene scene = doneButton.getScene();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("slGame.fxml"));
                Pane pane = (Pane) loader.load();
                scene.setRoot(pane);

                // Add game to controller
                SLGameController controller = loader.getController();
                controller.setGameClass(game);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Invalid game type");
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
