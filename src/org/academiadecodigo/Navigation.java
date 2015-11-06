package org.academiadecodigo;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Navigation {

    private static Navigation instance = null;

    private Navigation() {
    }

    public static Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }
        return instance;
    }

    private final int MIN_WIDTH = 1024; // window width
    private final int MIN_HEIGHT = 768; // window height
    private final String VIEW_PATH = "/org/academiadecodigo/view";

    private LinkedList<Scene> scenes = new LinkedList<Scene>(); // Navigation History
    private Map<String, Initializable> controllers = new HashMap<>(); //Container of controllers

    private Stage stage; // reference to the application window

    public void loadScreen(String view) {
        try {

            // Instantiate the view and the controller
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_PATH + "/" + view + ".fxml"));
            Parent root = fxmlLoader.load();

            //Store the controller
            controllers.put(view, fxmlLoader.getController());

            // Create a new scene and add it to the stack
            Scene scene = new Scene(root);
            scenes.push(scene);

            // Put the scene on the stage
            setScene(scene);

        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        // remove the current scene from the stack
        scenes.pop();

        // load the scene at the top of the stack
        setScene(scenes.peek());
    }

    private void setScene(Scene scene) {

        // set the scene
        stage.setScene(scene);

        // show the stage to reload
        stage.show();
    }


    public Initializable getController(String view) {
        return controllers.get(view);
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }
}
