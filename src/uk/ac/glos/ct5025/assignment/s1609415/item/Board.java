package uk.ac.glos.ct5025.assignment.s1609415.item;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.game.OXGame;
import uk.ac.glos.ct5025.assignment.s1609415.game.SLGame;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player.playerName;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private ArrayList<ArrayList<OXSquare>> oxSquares;
    private ArrayList<SLSquare> slSquares;
    private ArrayList<Line> lines;
    private Game game;
    private static final int X_POSITION = 700;
    private static final int Y_POSITION = 525;
    private static final int EDGE_PX = 600;


    public Board(Game game, int edgeSize) {
        this.oxSquares = new ArrayList<>();
        this.slSquares = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.game = game;

        createBoard(edgeSize);
    }

    private void createBoard(int edgeSize) {
        // Create UI game board
        try {
            int horizontal;
            int vertical;
            Location location;

            // Based on gameType
            if (getGame().getGameType() == Game.gameType.oxGame) {
                OXGame oxGame = (OXGame) getGame();
                ArrayList<OXSquare> squares;
                OXSquare square;

                // Create squares for board
                for (int row = 0; row < edgeSize; row++) {
                    vertical = (Y_POSITION - EDGE_PX / 2) + row * (EDGE_PX / edgeSize);
                    squares = new ArrayList<>();

                    // Create row of squares
                    for (int column = 0; column < edgeSize; column++) {

                        // Create square
                        horizontal = (X_POSITION - EDGE_PX / 2) + column * (EDGE_PX / edgeSize);
                        location = new Location( horizontal, vertical, edgeSize );
                        square = new OXSquare(oxGame, location, row, column);

                        squares.add( square );
                    }

                    addOxSquares(squares);
                }

                // Create game board
                getGame().getDrawClass().drawOXBoard(getOxSquares(), edgeSize);

            } else if (getGame().getGameType() == Game.gameType.slGame) {
                SLGame slGame = (SLGame) getGame();
                SLSquare square;
                int number;

                // Create squares for board
                for (int row = 0; row < edgeSize; row++) {
                    vertical = (Y_POSITION - EDGE_PX / 2) + row * (EDGE_PX / edgeSize);

                    // Create row of squares
                    for (int column = 0; column < edgeSize; column++) {

                        // Create square
                        horizontal = (X_POSITION - EDGE_PX / 2) + column * (EDGE_PX / edgeSize);
                        location = new Location( horizontal, vertical, edgeSize );
                        number = (row * edgeSize) + (edgeSize - column) + 1;
                        square = new SLSquare(slGame, number, location, row, column);

                        addSlSquare(square);
                    }
                }

                // Create game board
                //game.getDrawClass().drawSLBoard(getSlSquares(), edgeSize);

            } else {
                System.out.println("Board gameType does not match oxGame or slGame");
            }

        } catch (NullPointerException e) {
            System.out.println("Game subclass does not match gameType");
            e.printStackTrace();
        }
    }

    private ArrayList<ArrayList<OXSquare>> getOxSquares() {
        return oxSquares;
    }

    private ArrayList<SLSquare> getSlSquares() {
        return slSquares;
    }

    private void addOxSquares(ArrayList<OXSquare> oxSquares) {
        this.oxSquares.add( oxSquares );
    }

    private void addSlSquare(SLSquare slSquares) {
        this.slSquares.add( slSquares );
    }

    public OXSquare getOXSquare(int row, int column ) {
        if ( (row >= 0) && (row <= oxSquares.size() - 1) ) {
            ArrayList<OXSquare> squares = oxSquares.get(row);

            if ( (column >= 0) && (column <= squares.size() - 1) ) {

                return squares.get(column);
            }
        }

        return new OXSquare((OXGame) getGame(), new Location(-1, -1, 0), -1, -1);
    }

    private ArrayList<Line> getAllLines() {
        return lines;
    }

    protected void addLine(Line line) {
        lines.add(line);
    }

    private Game getGame() {
        return game;
    }

    protected void removeLine(Line line) {
        lines.remove(line);
    }

    public ArrayList<Line> getLines(playerName playerName, int lineLength, Line.endsFree endsFree) {
        ArrayList<Line> selectedLines = new ArrayList<>();
        boolean nameMatches = false;
        boolean lengthMatches = false;
        boolean endsFreeMatch = false;

        // Get lines that match all 3 conditions
        for (Line line: getAllLines() ) {
            nameMatches = ( line.getPlayerName() == playerName );
            lengthMatches = ( line.getLength() == lineLength );

            if(endsFree == Line.endsFree.any) {
                endsFreeMatch = true;
            } else {
                // Check which ends are empty
                boolean startEmpty = false;
                boolean endEmpty = false;

                OXSquare startSquare = line.getStartPoint();
                OXSquare endSquare = line.getEndPoint();

                // Get adjacent squares
                switch (line.getDirection()) {
                    case vertical:
                        startSquare = getOXSquare( startSquare.getRow()-1, startSquare.getColumn() );
                        endSquare = getOXSquare( endSquare.getRow()+1, endSquare.getColumn() );
                        break;

                    case horizontal:
                        startSquare = getOXSquare( startSquare.getRow(), startSquare.getColumn()-1 );
                        endSquare = getOXSquare( endSquare.getRow(), endSquare.getColumn()+1 );
                        break;

                    case diagonalBwd:
                        startSquare = getOXSquare( startSquare.getRow()+1, startSquare.getColumn()-1 );
                        endSquare = getOXSquare( endSquare.getRow()-1, endSquare.getColumn()+1 );
                        break;

                    case diagonalFwd:
                        startSquare = getOXSquare( startSquare.getRow()-1, startSquare.getColumn()-1 );
                        endSquare = getOXSquare( endSquare.getRow()+1, endSquare.getColumn()+1 );
                        break;

                }

                startEmpty = startSquare.isEmpty();
                endEmpty = endSquare.isEmpty();

                // set endsFreeMatch based on condition
                if(endsFree == Line.endsFree.both) {
                    endsFreeMatch = (startEmpty && endEmpty);
                } else if(endsFree == Line.endsFree.one) {
                    endsFreeMatch = (startEmpty ^ endEmpty);
                } else if (endsFree == Line.endsFree.none) {
                    endsFreeMatch = (!startEmpty && !endEmpty);
                }
            }

            // Select line if it matches
            if (nameMatches && lengthMatches && endsFreeMatch) {
                selectedLines.add(line);
            }
        }

        return selectedLines;
    }

    public OXSquare getEmptySquare() {
        // Get list of empty squares
        ArrayList<OXSquare> emptySquares = new ArrayList<>();

        for (ArrayList<OXSquare> squares: this.oxSquares) {
            for (OXSquare square: squares) {
                if(square.isEmpty()) {
                    emptySquares.add(square);
                }
            }
        }

        // Select random square from list
        int size = emptySquares.size();
        if (size > 1) {
            int rand = ThreadLocalRandom.current().nextInt(0, size - 1);
            OXSquare square = emptySquares.get(rand);
            return square;
        } else if(size == 1) {
            OXSquare square = emptySquares.get(0);
            return square;
        } else {
            return null;
        }
    }

    public SLSquare getSLSquare(int position) {
        return slSquares.get(position);
    }
}
