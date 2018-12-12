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
            ArrayList<Player> winner = getPlayers();
            Player player1 = getPlayers().get(0);
            Player player2 = getPlayers().get(1);

            try {
                DicePlayer dicePlayer1 = (DicePlayer) player1;
                DicePlayer dicePlayer2 = (DicePlayer) player2;
                Integer player1Score = dicePlayer1.getDice().getNumber();
                Integer player2Score = dicePlayer2.getDice().getNumber();
                System.out.println("Player1 = " + player1Score);
                System.out.println("Player2 = " + player2Score);

                if( player1Score > player2Score ) {
                    winner.remove( player2 );
                } else if ( player1Score < player2Score ) {
                    winner.remove( player1 );
                }

            } catch (ClassCastException e) {
                System.out.println("DicePlayer not used for DiceGame");
                System.out.println(e);
            }

            // Set winner
            setWinner( winner );
        }

        return finished;
    }

    public GameType getGameType() {
        return GameType.diceGame;
    }

}
