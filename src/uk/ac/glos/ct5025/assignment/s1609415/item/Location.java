package uk.ac.glos.ct5025.assignment.s1609415.item;

/**
 * This class represents the UI position and size of a UI item
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public class Location {

    private int horizontal;
    private int vertical;
    private int sizeFraction;


    public Location( int horizontal, int vertical, int sizeFraction ){
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.sizeFraction = sizeFraction;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public int getSizeFraction() {
        return sizeFraction;
    }
}
