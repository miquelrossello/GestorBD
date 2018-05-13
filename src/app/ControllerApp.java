package app;

import Connector_BD.Connector;
import Session.Session;
import app.Databases.Database;
import app.Databases.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable {

    @FXML
    private ListView<Button> databasesList;
    @FXML
    private Label titleLabel;
    @FXML
    private TableView<Table> tablesView;

    ObservableList<Table> tables = FXCollections.observableArrayList();

    Connection conn;

    @FXML
    MenuBar myMenuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableList();
        titleLabel.setText("Cap BD sel·leccionada");
    }

    private void setDatabaseTable(Database database) {
        tables = database.getTableList();
        TableColumn columnTables = new TableColumn("Tables");
        columnTables.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablesView.getColumns().clear();
        tablesView.setItems(tables);
        tablesView.getColumns().add(columnTables);
    }

    private void setTableList() {
        ObservableList<Database> dbList = getDatabases();
        ObservableList<Button> buttonList = FXCollections.observableArrayList();
        for (int x = 0; x < dbList.size(); x++) {
            Button btn = new Button(dbList.get(x).getName());
            int index = x;
            buttonList.add(btn);
            btn.setOnAction(event -> {
                displayInfoDatabase(btn.getText());
                setDatabaseTable(dbList.get(index));
            });
        }
        databasesList.setItems(buttonList);
    }

    private void displayInfoDatabase(String databaseName) {
        titleLabel.setText("BD sel·leccionada: " + databaseName);
    }

    private ObservableList<Database> getDatabases() {
        ObservableList<Database> databases = FXCollections.observableArrayList();
        try {
            conn = new Connector(Session.getInstance().getUsername(), Session.getInstance().getPassword(), "").getConnection();
            Statement statement = conn.createStatement();
            ResultSet rS = statement.executeQuery("SHOW DATABASES");
            while (rS.next()) {
                databases.add(new Database(rS.getString(1)));
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
