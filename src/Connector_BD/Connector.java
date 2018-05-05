package Connector_BD;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    private String username, password, database;
    public Connection connection;

    public Connection getConnection(String username, String password, String database, String server) {

        this.database = database;
        this.username = username;
        this.password = password;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database, username, password);
        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
=======
import java.sql.*;

public class Connector {

    private Connection connection;
    private String user;

    public boolean connectar(String user, String password, String database) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
            this.user = user;
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getUser() {
        return user;
    }
}

class TestSQL {
    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector();
        if (conn.connectar("root", "mrm1998", "")) {
            System.out.println(conn.getUser());
            System.out.println("Connexió exitosa!");
        }
>>>>>>> 8f2a2b5272f47297f97359398b472962b8ebcbf8
    }
}
