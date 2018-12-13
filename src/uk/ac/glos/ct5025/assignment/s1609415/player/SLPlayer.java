package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;
import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;
import uk.ac.glos.ct5025.assignment.s1609415.item.SLSquare;

public class SLPlayer implements Player {

    private Player.playerType playerType;
    private Dice dice;
    private playerName name;
    private Game game;
    private SLSquare currentSquare;


    public SLPlayer(Player.playerType playerType, playerName name) {
        this.playerType = playerType;
        this.name = name;
    }

    public void makeMove() {
        if (getType() == Player.playerType.Computer) {
            // Roll Dice
            getDice().roll();

            // Calculate new square
            SLSquare currentSquare = getCurrentSquare();
            int position = currentSquare.getNumber();
            int roll = getDice().getNumber();
            position += roll;

            if(position <= 100) {
                try {
                    // Move to new square
                    SLGame slGame = (SLGame) getGame();
                    SLSquare newSquare = slGame.getBoard().getSquare(position);
                    currentSquare.removePlayer(this);
                    newSquare.select( this, (roll == 6) );

                } catch (ClassCastException e) {
                    System.out.println("SLGame not used for S&L Game");
                    e.printStackTrace();
                }

            } else if (roll == 6) {
                // Try again
                makeMove();
            }
        }
    };

    public Player.playerType getType() {
        return this.playerType;
    }

    public playerName getName() {
        return this.name;
    }

    public Dice getDice() {
        return this.dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    private Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private SLSquare getCurrentSquare() {
        return this.currentSquare;
    }

    public void setCurrentSquare(SLSquare square) {
        this.currentSquare = square;
    }
}
