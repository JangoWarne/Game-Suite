package uk.ac.glos.ct5025.assignment.s1609415.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class extends application
 * it starts the game suite application
 * it loads the menu.fxml scene
 *
 * @author  Joshua Walker
 * @version 1.0
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent menuScene = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Game Suite");
        stage.setResizable(false);
        stage.setScene(new Scene(menuScene, 1400, 1000));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
