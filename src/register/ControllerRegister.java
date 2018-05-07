package register;

import Connector_BD.Connector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerRegister extends Application {
    @FXML
    String host = "localhost";
    @FXML
    private TextField userInput;
    @FXML
    private PasswordField passwordInput;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @FXML
    public void doRegister(ActionEvent event) {
        try {
            Connection conn = new Connector().getConnection();
            String createUser = "CREATE USER '" + userInput + "'@'" + host + "' IDENTIFIED BY '" + passwordInput + "';";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createUser);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkConnection(String user, String password) {
        Connector conn = new Connector();
        return conn.connectar(user, password, "");
    }

}
