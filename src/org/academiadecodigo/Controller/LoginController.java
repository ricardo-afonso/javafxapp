package org.academiadecodigo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.academiadecodigo.Model.MockUserService;
import org.academiadecodigo.Model.Navigation;
import org.academiadecodigo.Model.User;
import org.academiadecodigo.Model.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField emailField;
    @FXML
    Button loginButton;
    @FXML
    Label registerLabel;
    @FXML
    GridPane gridPane;
    @FXML
    Label emailLabel;
    @FXML
    Label passLabel;
    @FXML
    Label cadetLabel;
    @FXML
    Label confirmLabel;
    @FXML
    PasswordField confirmField;
    @FXML
    VBox bottomBox;
    @FXML
    Label systemMessages;

    private boolean isLogin = true;


    UserService userServ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridPane.getChildren().remove(emailField);
        gridPane.getChildren().remove(emailLabel);
        gridPane.getChildren().remove(confirmField);
        gridPane.getChildren().remove(confirmLabel);
        bottomBox.setPadding(new Insets(-45, 0, 0, 0));


    }

    @FXML
    public void onLoginButtonPressed(ActionEvent event) {
        if (isLogin) {
            if (userServ.authenticate(usernameField.getText(), passwordField.getText())) {
                systemMessages.setText("");
                systemMessages.setTextFill(Color.web("#339933"));
                systemMessages.setText("Authentication Succesful");
                Navigation.getInstance().loadScreen("main");


            } else {
                systemMessages.setText("");
                systemMessages.setTextFill(Color.web("#CC0000"));
                systemMessages.setText("Login Failed!");

            }
        } else {
            if (passwordField.getText().equals(confirmField.getText())) {
                userServ.addUser(new User(usernameField.getText(), passwordField.getText(), emailField.getText()));
                systemMessages.setText("");
                systemMessages.setTextFill(Color.web("#339933"));
                systemMessages.setText("Register Sucessful");
            } else {
                systemMessages.setText("");
                systemMessages.setTextFill(Color.web("#CC0000"));
                systemMessages.setText("Password Mismatch");
            }
        }
    }

    @FXML
    public void onRegisterButtonPressed(MouseEvent event) {
        if (isLogin) {
            gridPane.add(confirmLabel, 0, 2);
            gridPane.add(confirmField, 1, 2);
            gridPane.add(emailLabel, 0, 3);
            gridPane.add(emailField, 1, 3);
            loginButton.setText("Register");
            registerLabel.setText("Cancel");
            bottomBox.setPadding(new Insets(0, 0, 0, 0));
            isLogin = false;
        } else {
            gridPane.getChildren().remove(emailField);
            gridPane.getChildren().remove(emailLabel);
            gridPane.getChildren().remove(confirmField);
            gridPane.getChildren().remove(confirmLabel);
            bottomBox.setPadding(new Insets(-75, 0, 0, 0));
            loginButton.setText("Login");
            registerLabel.setText("New user? Sign up!");
            isLogin = true;
        }
    }

    public void setService (UserService us) {
        this.userServ = us;

    }


}
