package app;

import Connector_BD.Connector;
import Session.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable {

    @FXML
    private ListView<Button> databasesList;
    @FXML
    private Label testLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableList();
    }

    private void setTableList() {
        ObservableList<String> dbList = getDatabases();
        ObservableList<Button> buttonList = FXCollections.observableArrayList();
        for (int x = 0; x < dbList.size(); x++) {
            buttonList.add(new Button(dbList.get(x)));
            setListStyleButton(buttonList.get(x));
        }
        databasesList.setItems(buttonList);
    }

    private void setListStyleButton(Button button) {
        button.getStylesheets().add("/css/main.css");
        button.getStyleClass().add("button");
        button.setOnAction(event -> {
            displayInfoDatabase(button.getText());
        });
    }

    private void displayInfoDatabase(String databaseName) {
        testLabel.setText("BD sel·leccionada: " + databaseName);
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
