package uk.ac.glos.ct5025.assignment.s1609415.game;

import uk.ac.glos.ct5025.assignment.s1609415.player.Player;
import uk.ac.glos.ct5025.assignment.s1609415.ui.DrawUI;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Game {

    public enum GameType {
        diceGame, oxGame, slGame, none
    }

    private Player winner;
    private Player turn;
    private ArrayList<Player> players;
    private Instant startTime;
    private Instant endTime;
    private Boolean gameRunning;
    private DrawUI drawClass;


    public boolean isGameRunning() {
        return gameRunning;
    }

    private void setGameRunning(Boolean isRunning) {
        gameRunning = isRunning;
    }

    private Player getWinner() {
        return this.winner;
    }

    protected void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getTurn() {
        return turn;
    }

    protected void setTurn(Player turn) {
        this.turn = turn;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public DrawUI getDrawClass() {
        return this.drawClass;
    }

    protected void setDrawClass(DrawUI drawClass) {
        this.drawClass = drawClass;
    }

    private void startClock() {
        startTime = Instant.now();
    }

    private void stopClock() {
        endTime = Instant.now();
    }

    public Long getDuration() {
        return Duration.between(startTime, endTime).getSeconds();
    }

    public void endTurn() {
        // Find current player in arrayList
        Integer index = getPlayers().indexOf(getTurn());

        // Set player to next player in arrayList
        if(index + 1 > getPlayers().size() - 1) {
            index = 0;
        } else {
            index += 1;
        }
        setTurn( getPlayers().get(index) );

        if(isEnd()) {
            // End game
            endGame();
        } else {
            // Start next player move
            getTurn().makeMove();
        }
    }

    public void startGame() {
        // Create UI elements
        setupGame();

        // Start game
        setGameRunning(true);
        startClock();

        // Start first move
        setTurn( getPlayers().get(0) );
        getTurn().makeMove();
    }

    protected void setupGame() {
        // Setup UI

        // Setup Players

    }

    protected boolean isEnd() {
        // Check game state to see if it should finish
        return true;
    }

    public void endGame() {
        // End game
        setGameRunning(false);
        stopClock();

        // Draw UI game end
        getDrawClass().drawGameEnd( getWinner() );
    }

    public GameType getGameType() {
        return GameType.none;
    }
}
