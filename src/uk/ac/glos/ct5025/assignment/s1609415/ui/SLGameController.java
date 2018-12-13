package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.player.SLPlayer;
import javafx.scene.layout.TilePane;


public class SLGameController extends DiceGameController {

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
    private GridPane boardGridPane;


    public void setGameClass(Game game) {
        // Set DrawUI scene
        game.getDrawClass().setScene( game, backRegion.getScene(), winTextArea, new TilePane() );

        // Give Players Dice
        try {
            SLPlayer player1 = (SLPlayer) game.getPlayers().get(0);
            SLPlayer player2 = (SLPlayer) game.getPlayers().get(1);
            player1.setDice( getDice1() );
            player2.setDice( getDice2() );

            // Set Dice Text
            getDice1Label().setText( getDiceText(player1.getType()) );
            getDice2Label().setText( getDiceText(player2.getType()) );

        } catch (ClassCastException e) {
            System.out.println("SLPlayer not used for S&L Game");
            System.out.println(e);
        }

        // Start Game
        setGame(game);
        getGame().startGame();
    }



}
