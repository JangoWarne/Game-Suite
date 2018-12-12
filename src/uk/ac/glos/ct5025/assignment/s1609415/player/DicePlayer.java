package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;

public class DicePlayer implements Player {

    private PlayerType playerType;
    private Dice dice;
    private PlayerName name;
    private Game game;


    public DicePlayer(PlayerType playerType, PlayerName name) {
        this.playerType = playerType;
        this.name = name;
    }

    public void makeMove() {
        if (getType() == PlayerType.Computer) {
            // Roll Dice
            getDice().roll();
        }
    }

    public PlayerType getType() {
        return this.playerType;
    }

    public PlayerName getName() {
        return this.name;
    }

    public Dice getDice() {
        return this.dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    private Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
