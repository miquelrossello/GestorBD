package login;

import Connector_BD.Connector;
import javafx.application.Application;
import javafx.scene.control.Label;
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

    @Override
    public void start(Stage primaryStage) throws Exception {

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
