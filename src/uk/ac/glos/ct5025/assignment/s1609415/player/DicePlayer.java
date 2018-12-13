package uk.ac.glos.ct5025.assignment.s1609415.player;

import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;

public class DicePlayer implements Player {

    private Player.playerType playerType;
    private Dice dice;
    private playerName name;
    private Game game;


    public DicePlayer(Player.playerType playerType, playerName name) {
        this.playerType = playerType;
        this.name = name;
    }

    public void makeMove() {
        if (getType() == Player.playerType.Computer) {
            // Roll Dice
            getDice().roll();
        }
    }

    public Player.playerType getType() {
        return this.playerType;
    }

    public playerName getName() {
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
