package app;

import Connector_BD.Connector;
import Session.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable {

    @FXML
    private TableView<Database> databasesTable;
    @FXML
    private TableColumn<Database, String> databasesName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databasesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        databasesTable.setItems(getDatabases());
    }

    private ObservableList<Database> getDatabases() {
        String userDB = Session.getInstance().getUsername();
        String passDB = Session.getInstance().getPassword();
        ObservableList<Database> databases = FXCollections.observableArrayList();
        try {
            Connector conn = Connector.getInstance();
            conn.connectar(userDB, passDB, "");
            Statement statement = conn.getConnection().createStatement();
            ResultSet rS = statement.executeQuery("SHOW DATABASES");
            while (rS.next()) {
                databases.add(new Database(rS.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databases;
    }
}
