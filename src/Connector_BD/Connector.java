package Connector_BD;

import java.sql.*;

public class Connector {

    private Connection connection;
    private String user, password, database;

    public Connector(String user, String password, String database) {
        if (user != "" && user != null && password != "" && password != null) {
            this.user = user;
            this.password = password;
            this.database = database;
            connectar();
        }
    }

    public boolean connectar() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + database, user, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public String getUser() { return user; }
    public String getDatabase() { return database; }

    public void setUser(String user) { this.user = user; }
    public void setPassword(String password) { this.password = password; }
    public void setDatabase(String database) { this.database = database; }

    public Connection getConnection() {
        return connection;
    }

    public void changeDatabase(String newDatabase) {
        close();
        this.database = newDatabase;
        connectar();
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


class TestSQL {
    public static void main(String[] args) {
        Connector conn = new Connector("root", "mrm1998", "");
        conn.connectar();
        conn.changeDatabase("contactes");
        try {
            Statement statement = conn.getConnection().createStatement();
            ResultSet rS = statement.executeQuery("SHOW TABLES");
            while (rS.next()) {
                System.out.println(rS.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
