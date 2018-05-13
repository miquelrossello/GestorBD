package Session;

import Connector_BD.Connector;

public class Session {

    private static Session session;
    private String username;
    private String password;
    private Connector connectorDB;

    private Session() {

    }

    public Connector getConnectorDB() { return connectorDB; }

    public void setConnectorDB(Connector conn) { this.connectorDB = conn;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }
}

class TestSession {
    public static void main(String[] args) {
        Session sessionTst = Session.getInstance();
        sessionTst.setUsername("Prova");
        System.out.println(sessionTst.getUsername());
    }
}