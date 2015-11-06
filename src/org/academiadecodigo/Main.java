package org.academiadecodigo;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.controller.LoginController;
import org.academiadecodigo.model.JDBUserService;
import org.academiadecodigo.model.MockUserService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
//        root.getStylesheets().add("/login.css");
        primaryStage.setTitle("Academia de Código");
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");
        ((LoginController) Navigation.getInstance().getController("login")).setService(new JDBUserService());

    }


    public static void main(String[] args) {
        launch(args);
    }
}

