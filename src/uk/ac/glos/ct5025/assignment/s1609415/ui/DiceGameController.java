package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import uk.ac.glos.ct5025.assignment.s1609415.game.DiceGame;
import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;

import java.io.IOException;
import javafx.util.Duration;
import java.util.concurrent.ThreadLocalRandom;


public class DiceGameController {

    private DiceGame game;
    private Dice dice1;
    private Dice dice2;

    @FXML
    private Region backRegion;

    @FXML
    private Region dice1Region;

    @FXML
    private Region dice2Region;

    @FXML
    private ImageView dice1Image;

    @FXML
    private ImageView dice2Image;

    @FXML
    private Label dice1Label;

    @FXML
    private Label dice2Label;

    @FXML
    private TextArea winTextArea;



    @FXML
    private void initialize() {
        winTextArea.setVisible(false);

        // Handle UI events
        backRegion.setOnMouseClicked(this::backRegionHandle);
        dice1Region.setOnMouseClicked(this::dice1RegionHandle);
        dice2Region.setOnMouseClicked(this::dice2RegionHandle);

        // Create Dice
        dice1 = new Dice(this, Player.playerName.player1);
        dice2 = new Dice(this, Player.playerName.player2);
    }

    public void setGame(DiceGame game) {
        // Set DrawUI scene
        game.getDrawClass().setScene( backRegion.getScene(), winTextArea );

        // Give Players Dice
        try {
            DicePlayer player1 = (DicePlayer) game.getPlayers().get(0);
            DicePlayer player2 = (DicePlayer) game.getPlayers().get(1);
            player1.setDice( dice1 );
            player2.setDice( dice2 );

            // Set Dice Text
            dice1Label.setText( getDiceText(player1.getType()) );
            dice2Label.setText( getDiceText(player2.getType()) );

        } catch (ClassCastException e) {
            System.out.println("DicePlayer not used for DiceGame");
            System.out.println(e);
        }

        // Start Game
        this.game = game;
        this.game.startGame();
    }

    private void backRegionHandle(MouseEvent event) {
        try {
            Scene scene = backRegion.getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("menu.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dice1RegionHandle(MouseEvent event) {
        // Check correct player and game is running
        if((game.getTurn().getName() == Player.playerName.player1)
                && game.isGameRunning()
                && (game.getTurn().getType() == Player.playerType.Human)) {
            // Roll Dice
            dice1.roll();
        }
    }

    private void dice2RegionHandle(MouseEvent event) {
        // Check correct player and game is running
        if((game.getTurn().getName() == Player.playerName.player2)
                && game.isGameRunning()
                && (game.getTurn().getType() == Player.playerType.Human)) {
            // Roll Dice
            dice2.roll();
        }
    }

    public void dice1Roll( int roll ) {
        int num1 = ThreadLocalRandom.current().nextInt(1, 6+1);
        int num2 = ThreadLocalRandom.current().nextInt(1, 6+1);

        dice1Label.setText("");
        dice1Image.setImage( getImage(num1) );

        PauseTransition pause = new PauseTransition( Duration.millis(500) );
        pause.setOnFinished(event -> dice1Image.setImage( getImage(num2) ));

        PauseTransition pause2 = new PauseTransition( Duration.millis(500) );
        pause2.setOnFinished(event -> dice1Image.setImage( getImage(roll) ));

        PauseTransition pause3 = new PauseTransition( Duration.millis(500) );
        pause3.setOnFinished(event ->
                // End Turn
                game.endTurn()
        );

        SequentialTransition sequence = new SequentialTransition( pause, pause2, pause3 );
        sequence.play();
    }

    public void dice2Roll( int roll ) {
        int num1 = ThreadLocalRandom.current().nextInt(1, 6+1);
        int num2 = ThreadLocalRandom.current().nextInt(1, 6+1);

        dice2Label.setText("");
        dice2Image.setImage( getImage(num1) );

        PauseTransition pause = new PauseTransition( Duration.millis(500) );
        pause.setOnFinished(event -> dice2Image.setImage( getImage(num2) ));

        PauseTransition pause2 = new PauseTransition( Duration.millis(500) );
        pause2.setOnFinished(event -> dice2Image.setImage( getImage(roll) ));

        PauseTransition pause3 = new PauseTransition( Duration.millis(500) );
        pause3.setOnFinished(event ->
                // End Turn
                game.endTurn()
        );

        SequentialTransition sequence = new SequentialTransition( pause, pause2, pause3 );
        sequence.play();
    }

    private Image getImage(int roll ) {
        String imagePath = "dice_blank.png";

        switch (roll){
            case 1:
                imagePath = "dice_1.png";
                break;

            case 2:
                imagePath = "dice_2.png";
                break;

            case 3:
                imagePath = "dice_3.png";
                break;

            case 4:
                imagePath = "dice_4.png";
                break;

            case 5:
                imagePath = "dice_5.png";
                break;

            case 6:
                imagePath = "dice_6.png";
                break;

            default:
                break;
        }

        return new Image( getClass().getResourceAsStream(imagePath) );
    }

    private String getDiceText(Player.playerType playerType) {
        String text = "";

        if(playerType == Player.playerType.Human) {
            text = "Click to Roll";
        } else {
            text = "Computer";
        }

        return text;
    }
}
