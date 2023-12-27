package com.project.termproject.Controller;

import com.project.termproject.LoginUtil.SignupDTO;
import com.project.termproject.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerOfSignupPage {
    private MainApplication main;
    @FXML
    private Button backButton;
    @FXML
    private Button signUp;
    @FXML
    private Button reset;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField PSfield;
    private SignupDTO signupDTO;
    public void setMain(MainApplication main) {
        this.main = main;
    }

    public void signUpAction(ActionEvent event) {
        String userName = PSfield.getText();
        String passWord = password.getText();
        String retypePassword = password2.getText();
        System.out.println(userName + " " + passWord + " " + retypePassword);
        if (passWord.isBlank() || retypePassword.isBlank()) {
            showAlert("Password field cannot be empty.");
        } else if (!passWord.equals(retypePassword)) {
            showAlert("Retyped password did not match.");
            resetAction(event);
        }else {
            main.signupProductionCompany(userName,passWord);
        }
    }

    public void resetAction(ActionEvent event) {
        PSfield.setText(null);
        password.setText(null);
        password2.setText(null);
    }

    public void back(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String text) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText("Registration not successful");
        a.setContentText(text);
        a.showAndWait();
    }
}
