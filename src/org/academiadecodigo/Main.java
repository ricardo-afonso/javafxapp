package org.academiadecodigo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.academiadecodigo.Controller.LoginController;
import org.academiadecodigo.Model.MockUserService;
import org.academiadecodigo.Model.Navigation;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
//        root.getStylesheets().add("/login.css");
        primaryStage.setTitle("Academia de Código");
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");
        ((LoginController) Navigation.getInstance().getController("login")).setService(new MockUserService());

    }


    public static void main(String[] args) {
        launch(args);
    }
}

