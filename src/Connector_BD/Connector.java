package Connector_BD;

import java.sql.*;

public class Connector {
    private static Connector connInstance;

    private Connection connection;

    private Connector() {

    }

    public static Connector getInstance() {
        if (connInstance == null) {
            connInstance = new Connector();
        }
        return connInstance;
    }

    public boolean connectar(String user, String password, String database) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + database, user, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}


class TestSQL {
    public static void main(String[] args) throws SQLException {

    }
}
