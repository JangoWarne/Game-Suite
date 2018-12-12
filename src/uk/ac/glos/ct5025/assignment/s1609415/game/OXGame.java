package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.player.OXPlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.util.ArrayList;

public class OXGame extends Game{

    private Integer winLength;


    public OXGame(DrawUI drawClass, ArrayList<Player> players) {
        setDrawClass( drawClass );
        setPlayers( players );
    }

    private void setWinLength(Integer winLength) {
        this.winLength = winLength;
    }

    public Integer getWinLength() {
        return this.winLength;
    }

    protected void setupGame() {
        // Setup UI

        // Setup Players

    }
}
