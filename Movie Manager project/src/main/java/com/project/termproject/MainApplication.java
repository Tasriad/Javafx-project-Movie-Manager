package com.project.termproject;

import com.project.termproject.Client.WriteThreadClient;
import com.project.termproject.Controller.ControllerOfSignupPage;
import com.project.termproject.LoginUtil.LoginDTO;
import com.project.termproject.LoginUtil.SignupDTO;
import com.project.termproject.NetworkUtil.SocketWrapper;
import com.project.termproject.Controller.ControllerOfLoginPage;
import com.project.termproject.Controller.ControllerOfProductionCompanyPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApplication extends Application {
    private SocketWrapper socketWrapper;
    private UpdateFromReadThread updater = new UpdateFromReadThread();
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    @Override
    public void start(Stage Primarystage)  {
        try {
            stage = Primarystage;
            connectToServer();
            showLoginPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showLoginPage()  {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("scenes/LoginPage.fxml"));
            Parent root = loader.load();

            // Loading the controller
            ControllerOfLoginPage controller = loader.getController();
            controller.setMain(this);

            // Set the primary stage
            Image logo = new Image(MainApplication.class.getResourceAsStream("images/icon2.jpg"));
            stage.getIcons().add(logo);
            stage.setTitle("Movie Database Application");
            stage.setScene(new Scene(root, 1130, 700));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showProductionCompanyPage(LoginDTO loginDTO){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("scenes/ProductionCompanyPage2.fxml"));
            Parent root = loader.load();

            // Loading the controller
            ControllerOfProductionCompanyPage controller = loader.getController();
            controller.init(this);
            controller.hide();
            controller.labelSetter(loginDTO.getUserName());
            controller.setMain(this);

            // Set the primary stage
            stage.setTitle("Home Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showSignupPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("scenes/SignupPage.fxml"));
            Parent root = loader.load();

            ControllerOfSignupPage controller = loader.getController();
            controller.setMain(this);

            stage.setTitle("SignUp Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper= new SocketWrapper(serverAddress, serverPort);
        WriteThreadClient.setSocketWrapper(socketWrapper);
        ReadThread readThread = new ReadThread(this);
        readThread.setUpdateFromReadThread(updater);
    }
    public void showAlert(String msg1,String msg2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(msg1);
        alert.setHeaderText(msg1);
        alert.setContentText(msg2);
        alert.showAndWait();
    }
    public void signupProductionCompany(String username,String password){
        SignupDTO signupDTO = new SignupDTO(username,password);
        try {
            socketWrapper.write(signupDTO);
                showLoginPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}