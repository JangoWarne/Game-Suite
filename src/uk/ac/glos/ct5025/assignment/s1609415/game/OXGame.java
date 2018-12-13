package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.item.Board;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.util.ArrayList;

public class OXGame extends Game{

    private int winLength;
    private int edgeSize;
    private Board board;


    public OXGame(DrawUI drawClass, ArrayList<Player> players) {
        setDrawClass( drawClass );
        setPlayers( players );
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
        board = new Board( getEdgeSize() );

        // Setup Players

    }

    protected boolean isEnd() {
        // Check game state to see if it should finish
        boolean player1Finished = (getBoard().getLines(Player.playerName.player1, getWinLength(), any).size() > 0);
        boolean player2Finished = (getBoard().getLines(Player.playerName.player2, getWinLength(), any).size() > 0);
        boolean finished = ( player1Finished || player2Finished );

        if(finished) {
            // Find Winner
            ArrayList<Player> winner = new ArrayList<>();

            if (player1Finished){
                winner.add( getPlayers().get(0) );
            } else {
                winner.add( getPlayers().get(0) );
            }

            // Set winner
            setWinner( winner );
        }

        return finished;
    }
}
