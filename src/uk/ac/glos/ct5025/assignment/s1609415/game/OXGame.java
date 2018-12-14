package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.item.Board;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;
import uk.ac.glos.ct5025.assignment.s1609415.item.Line.endsFree;

import java.util.ArrayList;


/**
 * This class extends Game
 * it changes the game end condition and changes the setup to add a board
 * it adds winLength and edgeSize parameters
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public class OXGame extends Game{

    private int winLength;
    private int edgeSize;
    private Board board;


    public OXGame(DrawUI drawClass) {
        setDrawClass( drawClass );
    }

    public void setWinLength(int winLength) {
        this.winLength = winLength;
    }

    public int getWinLength() {
        return this.winLength;
    }

    public void setEdgeSize(int edgeSize) {
        this.edgeSize = edgeSize;
    }

    public int getEdgeSize() {
        return edgeSize;
    }

    public Board getBoard() {
        return board;
    }

    protected void setupGame() {
        // Setup UI
        board = new Board( this, getEdgeSize() );

        // Setup Players

    }

    protected boolean isEnd() {
        // Check game state to see if it should finish
        boolean player1Finished = (getBoard().getLines(Player.playerName.player1, getWinLength(), endsFree.any).size() > 0);
        boolean player2Finished = (getBoard().getLines(Player.playerName.player2, getWinLength(), endsFree.any).size() > 0);
        boolean fullBoard = (getBoard().getEmptySquare() == null);
        boolean finished = ( player1Finished || player2Finished );

        if(fullBoard) {
            // Draw
            setWinner( getPlayers() );
            finished = true;

        } else if(finished) {
            // Find Winner
            ArrayList<Player> winner = new ArrayList<>();

            if (player1Finished){
                winner.add( getPlayers().get(0) );
            } else {
                winner.add( getPlayers().get(1) );
            }

            // Set winner
            setWinner( winner );
        }

        return finished;
    }

    public gameType getGameType() {
        return gameType.oxGame;
    }
}
