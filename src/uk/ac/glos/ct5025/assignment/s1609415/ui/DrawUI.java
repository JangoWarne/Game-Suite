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
import javafx.util.Duration;
import uk.ac.glos.ct5025.assignment.s1609415.player.Player;

import java.io.IOException;


public class DrawUI {

    private Scene currentScene;
    private TextArea winTextArea;

    public void setScene(Scene scene, TextArea textArea) {
        currentScene = scene;
        winTextArea = textArea;
    }

    public void drawGameEnd(Player winner) {
        // Draw Winner text

        Parent pane = getScene().getRoot();
        String text = winner.getName().toString() + " Wins!";
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

    private Scene getScene(){
        return this.currentScene;
    }
}
