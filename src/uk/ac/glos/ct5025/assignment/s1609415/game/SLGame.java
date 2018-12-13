package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.item.Board;
import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;
import uk.ac.glos.ct5025.assignment.s1609415.item.SLSquare;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.util.ArrayList;

public class SLGame extends Game {

    private ArrayList<Dice> dice;
    private Board board;


    public SLGame(DrawUI drawClass, ArrayList<Dice> dice, ArrayList<Player> players) {
        setDrawClass( drawClass );
        setDice(dice);
        setPlayers( players );
    }

    private void setDice(ArrayList<Dice> dice) {
        this.dice = dice;
    }

    private ArrayList<Dice> getDice() {
        return this.dice;
    }

    public Board getBoard() {
        return board;
    }

    protected void setupGame() {
        // Setup UI
        board = new Board( 10 );

        // Setup Players

    }

    protected boolean isEnd() {
        // End if there is a player on the last square
        SLSquare lastSquare = getBoard().getSLSquare(100);
        if(lastSquare.hasPlayer()) {
            // Set winner
            ArrayList<Player> winner = new ArrayList<>();
            winner.add( lastSquare.getPlayer() );
            setWinner( winner );

            return true;
        }
    }
}
