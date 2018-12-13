package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;
import uk.ac.glos.ct5025.assignment.s1609415.item.Board;
import uk.ac.glos.ct5025.assignment.s1609415.item.Line;
import uk.ac.glos.ct5025.assignment.s1609415.item.OXSquare;

import java.util.ArrayList;

public class OXPlayer implements Player {

    private Player.playerType playerType;
    private playerName name;
    private Game game;


    public OXPlayer(Player.playerType playerType, playerName name) {
        this.playerType = playerType;
        this.name = name;
    }

    public void makeMove() {
        try {
            OXGame oxGame = (OXGame) getGame();
            Board board = oxGame.getBoard();

            // Get opponent name
            playerName opponent;
            if(getName() == playerName.player1){
                opponent = playerName.player2;
            } else {
                opponent = playerName.player1;
            }

            // Select winning move if possible, where: (length = winLength -1)
            if ( selectSquareForLines(getName(), oxGame.getWinLength()-1) ) {
                return;
            }

            // For each length of line starting at winLength-1
            for (int lineLength = oxGame.getWinLength()-1; lineLength > 0; lineLength--) {

                // Check for counter to opponent line
                if ( selectSquareForLines(opponent, lineLength) ) {
                    return;
                }

                // Check for move to extend personal line
                if ( selectSquareForLines(getName(), lineLength) ) {
                    return;
                }
            }

            // Else select empty square
            board.getEmptySquare().select();

        } catch (ClassCastException e) {
            System.out.println("OXGame not used for O&X Game");
            e.printStackTrace();
        }
    }

    private boolean selectSquareForLines(playerName playerName, int lineLength) {
        ArrayList<Line> playerLines;

        // Check for move to where both ends are free
        playerLines = board.getLines(getName(), lineLength, both);
        if ( selectEmptySquare( playerLines ) ) {
            return true;
        }

        // Check for move to where one end is free
        playerLines = board.getLines(getName(), lineLength, both);
        return selectEmptySquare( playerLines );
    }

    private boolean selectEmptySquare(ArrayList<Line> lines){
        try {
            // Select Empty Square and end on a line
            SLGame slGame = (SLGame) getGame();
            Board board = slGame.getBoard();

            for (Line line:lines) {
                // Get next in line Squares
                OXSquare nextStartSquare = board.getNextInLine(line.getStartPoint(), true);
                Boolean validStartSquare = false;
                if (nextStartSquare != null) {
                    validStartSquare = nextStartSquare.isEmpty();
                }

                OXSquare nextEndSquare = board.getNextInLine(line.getEndPoint(), false);
                Boolean validEndSquare = false;
                if (nextEndSquare != null) {
                    validEndSquare = nextEndSquare.isEmpty();
                }


                if ( validStartSquare ) {
                    // Select Start Square for move
                    nextStartSquare.select();
                    return true;
                } else if ( validEndSquare ) {
                    // Select End Square for move
                    nextEndSquare.select();
                    return true;
                }
            }

        } catch (ClassCastException e) {
            System.out.println("OXGame not used for O&X Game");
            e.printStackTrace();
        }

        return false;
    }

    public Player.playerType getType() {
        return this.playerType;
    }

    public playerName getName() {
        return this.name;
    }

    private Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
