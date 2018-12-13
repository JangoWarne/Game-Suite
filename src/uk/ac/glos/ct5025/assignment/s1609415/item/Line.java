package uk.ac.glos.ct5025.assignment.s1609415.item;

import java.util.ArrayList;

public class Line {

    public enum direction {
        horizontal, vertical, diagonalFwd, diagonalBwd
    }
    public enum endsFree {
        both, one, none, any
    }

    private OXSquare startPoint;
    private OXSquare endPoint;
    private ArrayList<OXSquare> squares;
    private Line.direction lineDirection;


    public Line(OXSquare startPoint, OXSquare endPoint, Line.direction direction) {
        setStartPoint( startPoint );
        setEndPoint( endPoint );
        this.lineDirection = direction;
    }

    public OXSquare getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(OXSquare startPoint) {
        this.startPoint = startPoint;
        addSquare(startPoint);
    }

    public OXSquare getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(OXSquare endPoint) {
        this.endPoint = endPoint;
        addSquare(endPoint);
    }

    public Line.direction getDirection() {
        return lineDirection;
    }

    public int getLength() {
        int rows = getEndPoint().getRow() - getStartPoint().getRow();
        int columns = getEndPoint().getColumn() - getStartPoint().getColumn();

        if( getDirection() == direction.vertical ) {
            return rows;
        } else {
            return columns;
        }
    }

    public void addSquare(OXSquare square) {
        this.squares.add( square );
    }

    public ArrayList<OXSquare> getSquares() {
        return squares;
    }
}
