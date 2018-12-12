package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;

public class OXPlayer implements Player {

    private PlayerType playerType;
    private PlayerName name;
    private Game game;


    public OXPlayer(PlayerType playerType, PlayerName name) {
        this.playerType = playerType;
        this.name = name;
    }

    public void makeMove() {

    };

    public PlayerType getType() {
        return this.playerType;
    }

    public PlayerName getName() {
        return this.name;
    }

    private Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
