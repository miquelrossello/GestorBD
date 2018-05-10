package app;

import Connector_BD.Connector;
import Session.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable {

    @FXML
    private ListView<String> databasesList;

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
            Connector conn = Connector.getInstance();
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

}
