package Connector_BD;

import java.sql.*;

public class Connector {

    private Connection connection;
    private String user;

    public boolean connectar(String user, String password, String database) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + database, user, password);
            this.user = user;
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + "", "root", "mamamia");
            this.user = user;
            return connection;
        } catch (SQLException e) {
            return connection;
        }
    }

    public String getUser() {
        return user;
    }
}

class TestSQL {
    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector();
        if (conn.connectar("root", "mamamia", "")) {
            System.out.println(conn.getUser());
            System.out.println("Connexió exitosa!");
        }
    }
}
