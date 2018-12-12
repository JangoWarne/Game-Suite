package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;
import uk.ac.glos.ct5025.assignment.s1609415.player.DicePlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.util.ArrayList;

public class DiceGame extends Game {


    public DiceGame(DrawUI drawClass) {
        setDrawClass( drawClass );
    }

    protected void setupGame() {
        // Setup UI

        // Setup Players

    }

    protected boolean isEnd() {
        // Check game state to see if it should finish
        boolean finished = getTurn().getName() == Player.PlayerName.player1;

        if(finished) {
            // Find Winner
            Integer highScore = 0;
            Player bestPlayer = getPlayers().get(0);

            for (Player player : getPlayers()) {

                try {
                    DicePlayer dicePlayer = (DicePlayer) player;
                    Integer playerScore = dicePlayer.getDice().getNumber();

                    if( playerScore > highScore ) {
                        highScore = playerScore;
                        bestPlayer = player;
                    }

                } catch (ClassCastException e) {
                    System.out.println("DicePlayer not used for DiceGame");
                    System.out.println(e);
                }
            }

            // Set winner
            setWinner( bestPlayer );
        }

        return finished;
    }

    public GameType getGameType() {
        return GameType.diceGame;
    }

}
