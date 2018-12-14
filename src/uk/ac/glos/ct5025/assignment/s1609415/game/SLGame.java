package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.item.Board;
import uk.ac.glos.ct5025.assignment.s1609415.item.SLSquare;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.util.ArrayList;


/**
 * This class extends Game
 * it changes the game end condition and changes the setup to add a board
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public class SLGame extends Game {

    private Board board;


    public SLGame(DrawUI drawClass) {
        setDrawClass( drawClass );
    }

    public Board getBoard() {
        return board;
    }

    protected void setupGame() {
        // Setup UI
        board = new Board( this, 10 );

        // Setup Players

    }

    protected boolean isEnd() {
        // End if there is a player on the last square
        SLSquare lastSquare = getBoard().getSLSquare(100);
        if(lastSquare.hasPlayer()) {
            // Set winner
            ArrayList<Player> winner = new ArrayList<>();
            winner.add( lastSquare.getPlayers().get(0) );
            setWinner( winner );

            return true;
        }

        return false;
    }

    public gameType getGameType() {
        return gameType.slGame;
    }
}
