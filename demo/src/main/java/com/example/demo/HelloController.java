package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane sub_form;
    @FXML
    private AnchorPane login_form;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private Button logIn_button;
    @FXML
    private Button sub_Sign_button;
    @FXML
    private Button sub_login_button;

    @FXML
    private Button su_signUpButton;
    @FXML
    private TextField si_username;
    @FXML
    private TextField su_username;
    @FXML
    private TextField su_address;
    @FXML
    private TextField si_password;
    @FXML
    private TextField su_password;
    @FXML
    private  Label edit_label;

    //DATABASE TOOLS

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    //********

    //

    public void login() throws SQLException {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            prepare.setString(1,si_username.getText());
            prepare.setString(2,si_password.getText());
            result=prepare.executeQuery();
            Alert alert;

            if(si_username.getText().isEmpty() || si_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all terms");
                alert.showAndWait();
            }else {
                if(result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login!!");
                    alert.showAndWait();
                      try{
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/demo/dashboard.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Dashboard");
                        stage.setScene(new Scene(root));
                        stage.show();
                        logIn_button.getScene().getWindow().hide();
                    }catch (Exception e){e.printStackTrace();}


                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username/password !!");
                    alert.showAndWait();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void signup(){
        String sql = "INSERT INTO admin (email, username, password) VALUES (?,?,?)";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");

            Alert alert;

            if (su_username.getText().isEmpty() || su_address.getText().isEmpty() || su_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all terms");
                alert.showAndWait();
            }else{
                if (su_password.getText().length() < 8){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("password invalid");
                    alert.showAndWait();
                }else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, su_address.getText());
                    prepare.setString(2, su_username.getText());
                    prepare.setString(3,su_password.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Signup");
                    alert.showAndWait();
                    prepare.executeUpdate();

                    su_address.setText("");
                    su_username.setText("");
                    su_password.setText("");

                }
            }



        }catch (Exception e){e.printStackTrace();}
    }
    public void signupSlider(){
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(300);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) ->{
            edit_label.setText("Login Account");

            sub_Sign_button.setVisible(false);
            sub_login_button.setVisible(true);
        });
    }
    public void liginSlider(){
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) ->{
            edit_label.setText("Create Account");
            sub_Sign_button.setVisible(true);
            sub_login_button.setVisible(false);
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}