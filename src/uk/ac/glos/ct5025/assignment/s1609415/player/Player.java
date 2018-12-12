package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;

public interface Player {

    public enum PlayerType{
        Human, Computer
    };

    public enum PlayerName{
        player1, player2
    };

    public void makeMove();
    public PlayerType getType();
    public PlayerName getName();
    public void setGame(Game game);
}
