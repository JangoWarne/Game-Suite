package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;

public interface Player {

    public enum playerType {
        Human, Computer
    };

    public enum playerName {
        player1, player2
    };

    public void makeMove();
    public playerType getType();
    public playerName getName();
    public void setGame(Game game);
}
