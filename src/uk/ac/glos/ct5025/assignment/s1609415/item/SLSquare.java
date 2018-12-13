package uk.ac.glos.ct5025.assignment.s1609415.item;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;
import uk.ac.glos.ct5025.assignment.s1609415.player.SLPlayer;

public class SLSquare implements Square {

    private int row;
    private int column;
    private Location location;
    private SLGame game;
    private int number;
    private SLPlayer player;
    private boolean hasPlayer;
    private SLSquare moveTo;
    private boolean hasAction;


    public SLSquare(SLGame slGame, int number, Location location, int row, int column) {
        this.game = slGame;
        this.number = number;
        this.location = location;
        this.row = row;
        this.column = column;
        this.hasAction = false;
    }

    public void select( SLPlayer player ) {
        // Move player piece
        setPlayer( player );
        getGame().getDrawClass().movePiece( this, player );

        // Wait for 1s
        PauseTransition pause = new PauseTransition( Duration.millis(1000) );
        pause.setOnFinished(event -> squareAction( player ));
        pause.play();
    }

    private void squareAction( SLPlayer player ) {
        if(getNumber() == 100) {
            // If last square end turn
            getGame().endTurn();

        } else if(hasAction()) {
            // If square has action
            removePlayer();

            // Select new square
            getMoveTo().select( player );

        } else if (player.getDice().getNumber() == 6) {
            // if player rolled 6, roll again
            player.makeMove();

        } else {
            getGame().endTurn();
        }
    }

    public void setMoveTo(SLSquare moveTo) {
        this.hasAction = true;
        this.moveTo = moveTo;
    }

    private boolean hasAction() {
        return hasAction;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public SLPlayer getPlayer() {
        return player;
    }

    private void setPlayer(SLPlayer player) {
        this.hasPlayer = true;
        this.player = player;
    }

    private void removePlayer() {
        this.hasPlayer = false;
    }

    public SLSquare getMoveTo() {
        return moveTo;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private SLGame getGame() {
        return game;
    }

    public int getNumber() {
        return number;
    }

    public Location getLocation() {
        return location;
    }
}
