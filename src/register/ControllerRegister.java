package register;

import Connector_BD.Connector;
import Session.Session;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerRegister extends Application {

    private String host = "localhost";
    @FXML
    private TextField userInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label accStatus;

    private Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @FXML
    public void doRegister(ActionEvent event) {
        try {
            conn = new Connector(Session.getInstance().getUsername(), Session.getInstance().getPassword(), "").getConnection();
            String createUser = "CREATE USER '" + userInput.getText() + "'@'" + host + "' IDENTIFIED BY '" + passwordInput.getText() + "';";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createUser);
            accStatus.setText("La compta d'usuari s'ha creat!");
        } catch (SQLException e) {
            e.printStackTrace();
            accStatus.setText("L'usuari no s'ha pogut crear! Comprova la conexio o si el usuari existeix.");
        }
    }

    public void back(ActionEvent event){
        try {
            Parent register = FXMLLoader.load(getClass().getResource("../app/app.fxml"));
            Scene registerScene = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.setResizable(false);
            window.setTitle("Login");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
