package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import uk.ac.glos.ct5025.assignment.s1609415.game.Game;
import uk.ac.glos.ct5025.assignment.s1609415.item.OXSquare;
import uk.ac.glos.ct5025.assignment.s1609415.player.OXPlayer;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class draws UI items on the current scene
 * it sets the action for the button for an OXSquare
 *
 * @author  Joshua Walker
 * @version 1.3
 */
public class DrawUI {

    private Scene currentScene;
    private TextArea winTextArea;
    private TilePane boardTilePane;
    private Game game;

    public void setScene( Game game, Scene scene, TextArea textArea, TilePane tilePane) {
        currentScene = scene;
        winTextArea = textArea;
        boardTilePane = tilePane;
        this.game = game;
    }

    public void drawGameEnd(ArrayList<Player> winner) {
        // Draw Winner text
        String text = "Draw";
        if (winner.size() == 1) {
            text = winner.get(0).getName().toString() + " Wins!";
        }

        Parent pane = getScene().getRoot();
        winTextArea.setText( text );
        winTextArea.setMaxHeight( 114 );
        winTextArea.setVisible( true );


        try {
            Parent newPane = FXMLLoader.load(getClass().getResource("menu.fxml"));

            // Wait 2s
            PauseTransition pause = new PauseTransition( Duration.millis(2000) );
            pause.setOnFinished(event ->
                    // Go back to main menu
                    getScene().setRoot( newPane )
            );
            pause.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPiece( OXSquare square ) {
        String text = String.valueOf( square.getPiece() );
        square.getButton().setText( text );
    }

    public void drawOXBoard(ArrayList<ArrayList<OXSquare>> squares2D, int edgeSize) {
        // set grid size
        resizeTilePane( edgeSize );

        // for each square
        for (ArrayList<OXSquare> squares1D: squares2D) {
            for (OXSquare square: squares1D) {

                // Add button to UI
                drawSquare( square, edgeSize );
            }
        }
    }

    private void resizeTilePane( int edgeSize ) {
        getBoardTilePane().setPrefRows(edgeSize);
        getBoardTilePane().setPrefColumns(edgeSize);
    }

    private void drawSquare( OXSquare square, int edgeSize ) {
        // add square to gridpane
        Button button = new Button();
        double buttonSize = (600 / (double) edgeSize) - 1;
        int textSize;

        if(edgeSize == 3) {
            textSize = 70;
        } else if(edgeSize == 4) {
            textSize = 60;
        } else if(edgeSize == 5) {
            textSize = 50;
        } else if(edgeSize == 6) {
            textSize = 40;
        } else if(edgeSize == 7) {
            textSize = 30;
        } else if(edgeSize == 8) {
            textSize = 25;
        } else if(edgeSize == 9) {
            textSize = 20;
        } else {
            textSize = 15;
        }

        button.setPrefSize( buttonSize, buttonSize);
        button.setFont( Font.font("System", FontWeight.BOLD, textSize) );
        button.setOnAction(e -> squareButtonHandle(square));

        square.setButton(button);
        getBoardTilePane().getChildren().add( button );
    }

    private void squareButtonHandle(OXSquare square) {
        // Check correct player and game is running
        if((getGame().getTurn().getName() == Player.playerName.player1)
                && getGame().isGameRunning()
                && (getGame().getTurn().getType() == Player.playerType.Human)) {

            try {
                // select square
                square.select((OXPlayer) getGame().getTurn());

            } catch (ClassCastException e) {
                System.out.println("OXPlayer not used for O&X Game");
                e.printStackTrace();
            }
        }
    }

    private TilePane getBoardTilePane() {
        return boardTilePane;
    }

    private Scene getScene(){
        return this.currentScene;
    }

    public Game getGame() {
        return game;
    }
}
