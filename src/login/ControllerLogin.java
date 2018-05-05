package login;

import Connector_BD.Connector;
import javafx.application.Application;
<<<<<<< HEAD
import javafx.scene.control.Label;
=======
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
>>>>>>> 8f2a2b5272f47297f97359398b472962b8ebcbf8
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.w3c.dom.Text;

import java.sql.Connection;

public class ControllerLogin extends Application {
    public TextField server;
    public TextField database;
    public TextField user;
    public TextField pass;
    public Label connection;

<<<<<<< HEAD
=======

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    @FXML
    private TextField userInput;
    @FXML
    private PasswordField passwordInput;

    @FXML
    public void doLogin(ActionEvent event) {
        String user = userInput.getText();
        String password = passwordInput.getText();
        if (checkConnection(user, password)) {
            try {
                Parent mainApp = FXMLLoader.load(getClass().getResource("/app/app.fxml"));
                Scene mainAppScene = new Scene(mainApp);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(mainAppScene);
                window.setResizable(false);
                window.setTitle("Pagina principal");
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Can't connect!");
        }
    }

    private boolean checkConnection(String user, String password) {
        Connector conn = new Connector();
        return conn.connectar(user, password, "");
    }

>>>>>>> 8f2a2b5272f47297f97359398b472962b8ebcbf8
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loginButton(ActionEvent actionEvent) {
        Connector connector = new Connector();
        Connection connections = connector.getConnection(changeType(user), changeType(pass), changeType(database), changeType(server));

        if (connections != null) {
            connection.setText("SUCCESFULL");
        } else {
            connection.setText("FAILED");
        }
    }

    public void registerButton(ActionEvent actionEvent) {
        connection.setText(pass.getText());
    }

    private String changeType(TextField text){
        return (String) text.getText();
    }
}
