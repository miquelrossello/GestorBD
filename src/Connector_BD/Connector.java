package Connector_BD;

import java.sql.*;

public class Connector {

    public static Connection createConnection(String username, String password) {
        String url = "jdbc:mysql://localhost/contactes";
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class TestSQL {
    public static void main(String[] args) throws SQLException {
        Connection conn = Connector.createConnection("root", "mrm1998");
        Statement stmnt = conn.createStatement();
        ResultSet rS = stmnt.executeQuery("SHOW DATABASES");
        while (rS.next()) {
            System.out.println(rS.getString(1));
        }
    }
}
