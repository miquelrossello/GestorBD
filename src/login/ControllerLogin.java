package login;

import Connector_BD.Connector;
import Session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    @FXML
    private TextField userInput;
    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label connectionStatus;

    @FXML
    public void doLogin(ActionEvent event) {
        String user = userInput.getText();
        String password = passwordInput.getText();
        if (checkConnection(user, password)) {
            Session sessionStart = Session.getInstance();
            sessionStart.setUsername(user);
            sessionStart.setPassword(password);
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
            connectionStatus.setText("FAILED");
            System.err.println("Can't connect!");
        }
    }

    private boolean checkConnection(String user, String password) {
        Connector conn = Connector.getInstance();
        return conn.connectar(user, password, "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
