package uk.ac.glos.ct5025.assignment.s1609415.item;

import uk.ac.glos.ct5025.assignment.s1609415.player.Player;

/**
 * This is the interface for game board squares
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public interface Square {
    int getRow();
    int getColumn();
    Location getLocation();
}
