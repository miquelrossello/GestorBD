package login;

import Connector_BD.Connector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
