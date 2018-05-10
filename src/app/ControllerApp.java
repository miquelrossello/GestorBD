package app;

import Connector_BD.Connector;
import Session.Session;
import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable {

    @FXML
    private ListView<String> databasesList;

    Connector conn;

    @FXML
    MenuBar myMenuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableList();
        databasesList.setItems(getDatabases());
    }

    private void setTableList() {
        ObservableList<String> dbList = databasesList.getItems();
        for (int x = 0; x < dbList.size(); x++) {

        }
    }

    private ObservableList<String> getDatabases() {
        String userDB = Session.getInstance().getUsername();
        String passDB = Session.getInstance().getPassword();
        ObservableList<String> databases = FXCollections.observableArrayList();
        try {
            conn = Connector.getInstance();
            conn.connectar(userDB, passDB, "");
            Statement statement = conn.getConnection().createStatement();
            ResultSet rS = statement.executeQuery("SHOW DATABASES");
            while (rS.next()) {
                databases.add(new Database(rS.getString(1)).getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databases;
    }

    @FXML
    public void menuRegister() {
        try {
            Parent register = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
            Scene registerScene = new Scene(register);
            Stage window = (Stage) myMenuBar.getScene().getWindow();
            window.setScene(registerScene);
            window.setResizable(false);
            window.setTitle("Register");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void menuLogout() {
        try {
            conn.close();
            Parent register = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
            Scene registerScene = new Scene(register);
            Stage window = (Stage) myMenuBar.getScene().getWindow();
            window.setScene(registerScene);
            window.setResizable(false);
            window.setTitle("Login");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
