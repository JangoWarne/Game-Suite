package uk.ac.glos.ct5025.assignment.s1609415.item;

import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DiceGameController;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private Integer number;
    private DiceGameController windowUI;
    private Player.PlayerName playerName;


    public Dice(DiceGameController windowUI, Player.PlayerName playerName) {
        this.windowUI = windowUI;
        this.playerName = playerName;
    }

    public void roll(){
        this.number = ThreadLocalRandom.current().nextInt(1, 6+1);

        if(playerName == Player.PlayerName.player1) {
            windowUI.dice1Roll( this.number );
        } else {
            windowUI.dice2Roll( this.number );
        }
    }

    public Integer getNumber(){
        return this.number;
    }
}
