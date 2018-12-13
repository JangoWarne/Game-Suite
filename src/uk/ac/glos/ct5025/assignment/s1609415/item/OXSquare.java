package uk.ac.glos.ct5025.assignment.s1609415.item;

import javafx.scene.control.Button;
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.player.OXPlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player.playerName;

import java.util.ArrayList;

public class OXSquare implements Square {

    private int row;
    private int column;
    private Location location;
    private char piece;
    private OXGame game;
    private ArrayList<Line> lines;
    private Button button;


    public OXSquare(OXGame oxGame, Location location, int row, int column) {
        this.lines = new ArrayList<>();
        this.game = oxGame;
        this.location = location;
        this.row = row;
        this.column = column;
    }

    public void select( OXPlayer player ) {
        // Check game is running
        if(getGame().isGameRunning()) {
            System.out.println(player.getName() + " " + getColumn() + " " + getRow());

            // Check if square is empty
            if (isEmpty()) {
                // Add Player Piece
                piece = player.getPiece();
                getGame().getDrawClass().drawPiece(this);

                // Add to adjacent lines
                mergeLines(player.getName(), Line.direction.vertical);
                mergeLines(player.getName(), Line.direction.horizontal);
                mergeLines(player.getName(), Line.direction.diagonalBwd);
                mergeLines(player.getName(), Line.direction.diagonalFwd);

                getGame().endTurn();
            }
        }
    }

    private void mergeLines( playerName playerName, Line.direction direction ) {
        OXSquare square1 = this;
        OXSquare square2 = this;

        // Get adjacent squares
        switch (direction) {
            case vertical:
                square1 = getGame().getBoard().getOXSquare( getRow()-1, getColumn() );
                square2 = getGame().getBoard().getOXSquare( getRow()+1, getColumn() );
                break;

            case horizontal:
                square1 = getGame().getBoard().getOXSquare( getRow(), getColumn()-1 );
                square2 = getGame().getBoard().getOXSquare( getRow(), getColumn()+1 );
                break;

            case diagonalBwd:
                square1 = getGame().getBoard().getOXSquare( getRow()+1, getColumn()-1 );
                square2 = getGame().getBoard().getOXSquare( getRow()-1, getColumn()+1 );
                break;

            case diagonalFwd:
                square1 = getGame().getBoard().getOXSquare( getRow()-1, getColumn()-1 );
                square2 = getGame().getBoard().getOXSquare( getRow()+1, getColumn()+1 );
                break;

        }

        // Get Properties
        boolean is1Usable = !square1.isEmpty() && (square1.getPiece() == getPiece());
        boolean is2Usable = !square2.isEmpty() && (square2.getPiece() == getPiece());
        ArrayList<Line> lines;
        Line line1 = null;
        Line line2 = null;

        // Get square1 in-line line
        if( is1Usable ) {
            lines = square1.getLines();

            for (Line line:lines) {
                if(line.getDirection() == direction) {
                    line1 = line;
                    break;
                }
            }
        }

        // Get square2 in-line line
        if( is2Usable ) {
            lines = square2.getLines();

            for (Line line:lines) {
                if(line.getDirection() == direction) {
                    line2 = line;
                    break;
                }
            }
        }

        // Create/Merge line for direction
        try {
            if ( is1Usable && is2Usable ) {
                // Merge square1 and square2 lines
                line1.setEndPoint( line2.getEndPoint() );

                for (OXSquare square: line2.getSquares() ) {
                    square.removeLine(line2);
                    square.addLine(line1);
                }

                getGame().getBoard().removeLine( line2 );
                addLine( line1 );

            } else if ( is1Usable ) {
                // Merge with square1 line
                line1.setEndPoint( this );
                addLine( line1 );

            } else if ( is2Usable ) {
                // Merge with square2 line
                line2.setStartPoint( this );
                addLine( line2 );

            } else {
                // create new line
                Line newLine = new Line( playerName, this, this,  direction);
                getGame().getBoard().addLine( newLine );
                addLine( newLine );
            }

        } catch (NullPointerException e) {
            System.out.println("Line object does not exist");
            e.printStackTrace();
        }
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Location getLocation() {
        return this.location;
    }

    public boolean isEmpty() {
        return (piece == '\u0000');
    }

    private OXGame getGame() {
        return game;
    }

    private ArrayList<Line> getLines() {
        return lines;
    }

    private void addLine( Line line ) {
        this.lines.add( line );
    }

    private void removeLine( Line line ) {
        this.lines.remove( line );
    }

    public char getPiece() {
        return piece;
    }
}
