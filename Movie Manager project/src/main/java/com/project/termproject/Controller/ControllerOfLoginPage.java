package com.project.termproject.Controller;

import com.project.termproject.LoginUtil.LoginDTO;
import com.project.termproject.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerOfLoginPage {
    private MainApplication main;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField productionCompanyField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signinButton;
    @FXML
    private Button signupButton;

    public void setMain(MainApplication main) {
        this.main = main;
    }

    public void signin(ActionEvent event) throws IOException{
        String userName = productionCompanyField.getText();
        String password = passwordField.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        System.out.println(userName + " " + password);
        try {
            main.getSocketWrapper().write(loginDTO);
            System.out.println(userName + " " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void signup(ActionEvent event) throws IOException{
        main.showSignupPage();
    }
}
