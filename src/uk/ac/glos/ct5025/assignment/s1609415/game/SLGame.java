package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.item.Dice;
import uk.ac.glos.ct5025.assignment.s1609415.player.SLPlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.util.ArrayList;

public class SLGame extends Game {

    private ArrayList<Dice> dice;


    public SLGame(DrawUI drawClass, ArrayList<Dice> dice, ArrayList<Player> players) {
        setDrawClass( drawClass );
        setDice(dice);
        setPlayers( players );
    }

    private void setDice(ArrayList<Dice> dice) {
        this.dice = dice;
    }

    private ArrayList<Dice> getDice() {
        return this.dice;
    }

    protected void setupGame() {
        // Setup UI

        // Setup Players
        Integer index = 0;
        Boolean multipleDice = (getPlayers().size() == getDice().size());

        for (Player player : getPlayers()) {

            try {
                SLPlayer slPlayer = (SLPlayer) player;

                if (multipleDice) {
                    // use one dice per player
                    slPlayer.setDice(getDice().get(index));
                    index += 1;
                } else {
                    // use one dice for all players
                    slPlayer.setDice(getDice().get(0));
                }
            } catch (ClassCastException e) {
                System.out.println("SLPlayer not used for SLGame");
                System.out.println(e);
            }
        }
    }
}
