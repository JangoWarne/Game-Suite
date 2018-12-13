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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import uk.ac.glos.ct5025.assignment.s1609415.game.DiceGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;

import java.io.IOException;
import javafx.util.Duration;
import java.util.concurrent.ThreadLocalRandom;


public class DiceGameController {

    private Game game;
    private Dice dice1;
    private Dice dice2;

    @FXML
    protected Region backRegion;
    @FXML
    protected Region dice1Region;
    @FXML
    protected Region dice2Region;
    @FXML
    protected ImageView dice1Image;
    @FXML
    protected ImageView dice2Image;
    @FXML
    protected Label dice1Label;
    @FXML
    protected Label dice2Label;
    @FXML
    protected TextArea winTextArea;



    @FXML
    private void initialize() {
        getWinTextArea().setVisible(false);

        // Handle UI events
        getBackRegion().setOnMouseClicked(this::backRegionHandle);
        getDice1Region().setOnMouseClicked(this::dice1RegionHandle);
        getDice2Region().setOnMouseClicked(this::dice2RegionHandle);

        // Create Dice
        setDice1( new Dice(this, Player.playerName.player1) );
        setDice2( new Dice(this, Player.playerName.player2) );
    }

    public void setGameClass(Game game) {
        // Set DrawUI scene
        game.getDrawClass().setScene( game, getBackRegion().getScene(), getWinTextArea(), new TilePane());

        // Give Players Dice
        try {
            DicePlayer player1 = (DicePlayer) game.getPlayers().get(0);
            DicePlayer player2 = (DicePlayer) game.getPlayers().get(1);
            player1.setDice( getDice1() );
            player2.setDice( getDice2() );

            // Set Dice Text
            getDice1Label().setText( getDiceText(player1.getType()) );
            getDice2Label().setText( getDiceText(player2.getType()) );

        } catch (ClassCastException e) {
            System.out.println("DicePlayer not used for DiceGame");
            System.out.println(e);
        }

        // Start Game
        setGame(game);
        getGame().startGame();
    }

    protected void backRegionHandle(MouseEvent event) {
        try {
            Scene scene = getBackRegion().getScene();
            scene.setRoot(FXMLLoader.load(getClass().getResource("menu.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void dice1RegionHandle(MouseEvent event) {
        // Check correct player and game is running
        if((getGame().getTurn().getName() == Player.playerName.player1)
                && getGame().isGameRunning()
                && (getGame().getTurn().getType() == Player.playerType.Human)) {
            // Roll Dice
            dice1.roll();
        }
    }

    protected void dice2RegionHandle(MouseEvent event) {
        // Check correct player and game is running
        if((getGame().getTurn().getName() == Player.playerName.player2)
                && getGame().isGameRunning()
                && (getGame().getTurn().getType() == Player.playerType.Human)) {
            // Roll Dice
            dice2.roll();
        }
    }

    public void dice1Roll( int roll ) {
        int num1 = ThreadLocalRandom.current().nextInt(1, 6+1);
        int num2 = ThreadLocalRandom.current().nextInt(1, 6+1);

        getDice1Label().setText("");
        getDice1Image().setImage( getImage(num1) );

        PauseTransition pause = new PauseTransition( Duration.millis(500) );
        pause.setOnFinished(event -> getDice1Image().setImage( getImage(num2) ));

        PauseTransition pause2 = new PauseTransition( Duration.millis(500) );
        pause2.setOnFinished(event -> getDice1Image().setImage( getImage(roll) ));

        PauseTransition pause3 = new PauseTransition( Duration.millis(500) );
        pause3.setOnFinished(event ->
                // End Turn
                getGame().endTurn()
        );

        SequentialTransition sequence = new SequentialTransition( pause, pause2, pause3 );
        sequence.play();
    }

    public void dice2Roll( int roll ) {
        int num1 = ThreadLocalRandom.current().nextInt(1, 6+1);
        int num2 = ThreadLocalRandom.current().nextInt(1, 6+1);

        getDice2Label().setText("");
        getDice2Image().setImage( getImage(num1) );

        PauseTransition pause = new PauseTransition( Duration.millis(500) );
        pause.setOnFinished(event -> getDice2Image().setImage( getImage(num2) ));

        PauseTransition pause2 = new PauseTransition( Duration.millis(500) );
        pause2.setOnFinished(event -> getDice2Image().setImage( getImage(roll) ));

        PauseTransition pause3 = new PauseTransition( Duration.millis(500) );
        pause3.setOnFinished(event ->
                // End Turn
                getGame().endTurn()
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

    protected String getDiceText(Player.playerType playerType) {
        String text = "";

        if(playerType == Player.playerType.Human) {
            text = "Click to Roll";
        } else {
            text = "Computer";
        }

        return text;
    }

    public Dice getDice1() {
        return dice1;
    }

    public void setDice1(Dice dice1) {
        this.dice1 = dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public void setDice2(Dice dice2) {
        this.dice2 = dice2;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Region getBackRegion() {
        return backRegion;
    }

    public TextArea getWinTextArea() {
        return winTextArea;
    }

    public ImageView getDice1Image() {
        return dice1Image;
    }

    public ImageView getDice2Image() {
        return dice2Image;
    }

    public Label getDice1Label() {
        return dice1Label;
    }

    public Region getDice1Region() {
        return dice1Region;
    }

    public Label getDice2Label() {
        return dice2Label;
    }

    public Region getDice2Region() {
        return dice2Region;
    }
}
