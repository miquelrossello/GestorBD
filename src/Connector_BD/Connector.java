package Connector_BD;

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
    }
}
