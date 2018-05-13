package app.Databases;

import Connector_BD.Connector;
import Session.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Database {

    private SimpleStringProperty name;
    private ObservableList<Table> tableList = FXCollections.observableArrayList();

    public Database(String name) {
        if (name != null) {
            this.name = new SimpleStringProperty(name);
            setTablesDatabase();
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    private void setTablesDatabase() { //Problema amb les gestinos de connexions SQL ???
        try {
            Connection conn = new Connector(Session.getInstance().getUsername(), Session.getInstance().getPassword(), getName()).getConnection();
            Statement statement = conn.createStatement();
            ResultSet rS = statement.executeQuery("SHOW TABLES");
            while (rS.next()) {
                tableList.add(new Table(rS.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Table> getTableList() {
        return tableList;
    }
}

class TestDatabase {
     public static void main(String[] args) {
         Session.getInstance().setUsername("root");
         Session.getInstance().setPassword("mrm1998");
         Database dbT = new Database("mysql");
         System.out.println(Arrays.toString(dbT.getTableList().toArray()));
     }
}
