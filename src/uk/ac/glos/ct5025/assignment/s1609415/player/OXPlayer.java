package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;
import uk.ac.glos.ct5025.assignment.s1609415.item.Board;
import uk.ac.glos.ct5025.assignment.s1609415.item.Line;
import uk.ac.glos.ct5025.assignment.s1609415.item.OXSquare;
import uk.ac.glos.ct5025.assignment.s1609415.item.Line.endsFree;

import java.util.ArrayList;

/**
 * This class implements Player
 * it sets the make move behaviour
 * (move is based on lines in Board)
 * it defines a constructor
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public class OXPlayer implements Player {

    private Player.playerType playerType;
    private playerName name;
    private Game game;
    private char piece;


    public OXPlayer( Game game, Player.playerType playerType, playerName name) {
        this.playerType = playerType;
        this.name = name;
        this.game = game;

        if(name == playerName.player1) {
            piece = 'X';
        } else {
            piece = 'O';
        }
    }

    public void makeMove() {
        if (getType() == Player.playerType.Computer) {
            try {
                OXGame oxGame = (OXGame) getGame();
                Board board = oxGame.getBoard();

                // Get opponent name
                playerName opponent;
                if (getName() == playerName.player1) {
                    opponent = playerName.player2;
                } else {
                    opponent = playerName.player1;
                }

                // Select winning move if possible, where: (length = winLength -1)
                if (selectSquareForLines(board, getName(), oxGame.getWinLength() - 1)) {
                    return;
                }

                // For each length of line starting at winLength-1
                for (int lineLength = oxGame.getWinLength() - 1; lineLength > 0; lineLength--) {

                    // Check for counter to opponent line
                    if (selectSquareForLines(board, opponent, lineLength)) {
                        return;
                    }

                    // Check for move to extend personal line
                    if (selectSquareForLines(board, getName(), lineLength)) {
                        return;
                    }
                }

                // Else select empty square
                OXSquare emptySquare = board.getEmptySquare();
                if (emptySquare != null) {
                    emptySquare.select(this);
                }

            } catch (ClassCastException e) {
                System.out.println("OXGame not used for O&X Game");
                e.printStackTrace();
            }
        }
    }

    private boolean selectSquareForLines(Board board, playerName playerName, int lineLength) {
        ArrayList<Line> playerLines;

        // Check for move to where both ends are free
        playerLines = board.getLines(getName(), lineLength, endsFree.both);
        if ( selectEmptySquare( playerLines ) ) {
            return true;
        }

        // Check for move to where one end is free
        playerLines = board.getLines(getName(), lineLength, endsFree.one);
        return selectEmptySquare( playerLines );
    }

    private boolean selectEmptySquare(ArrayList<Line> lines){
        // Select Empty Square at end of line
        for (Line line:lines) {
            // Get next in line Squares
            OXSquare nextStartSquare = getNextInLine(line, true);
            boolean validStartSquare = false;
            if (nextStartSquare != null) {
                validStartSquare = nextStartSquare.isEmpty();
            }

            OXSquare nextEndSquare = getNextInLine(line, false);
            boolean validEndSquare = false;
            if (nextEndSquare != null) {
                validEndSquare = nextEndSquare.isEmpty();
            }


            if ( validStartSquare ) {
                // Select Start Square for move
                nextStartSquare.select( this );
                return true;

            } else if ( validEndSquare ) {
                // Select End Square for move
                nextEndSquare.select( this );
                return true;
            }
        }

        return false;
    }

    private OXSquare getNextInLine(Line line, boolean startEnd) {
        // Get next square in-line beyond end of line
        try {
            OXGame oxGame = (OXGame) getGame();
            Board board = oxGame.getBoard();

            Line.direction direction = line.getDirection();

            OXSquare square;
            if( startEnd ) {
                square = line.getStartPoint();

                // Get adjacent squares
                switch (direction) {
                    case vertical:
                        square = board.getOXSquare( square.getRow()-1, square.getColumn() );
                        break;

                    case horizontal:
                        square = board.getOXSquare( square.getRow(), square.getColumn()-1 );
                        break;

                    case diagonalBwd:
                        square = board.getOXSquare( square.getRow()+1, square.getColumn()-1 );
                        break;

                    case diagonalFwd:
                        square = board.getOXSquare( square.getRow()-1, square.getColumn()-1 );
                        break;

                }
            } else {
                square = line.getEndPoint();

                // Get adjacent squares
                switch (direction) {
                    case vertical:
                        square = board.getOXSquare( square.getRow()+1, square.getColumn() );
                        break;

                    case horizontal:
                        square = board.getOXSquare( square.getRow(), square.getColumn()+1 );
                        break;

                    case diagonalBwd:
                        square = board.getOXSquare( square.getRow()-1, square.getColumn()+1 );
                        break;

                    case diagonalFwd:
                        square = board.getOXSquare( square.getRow()+1, square.getColumn()+1 );
                        break;

                }
            }

            if (square.getRow() != -1) {
                return square;
            } else {
                return null;
            }


        } catch (ClassCastException e) {
            System.out.println("OXGame not used for O&X Game");
            e.printStackTrace();
        }

        return null;
    }

    public Player.playerType getType() {
        return this.playerType;
    }

    public char getPiece() {
        return piece;
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
