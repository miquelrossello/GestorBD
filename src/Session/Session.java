package Session;

import java.sql.Connection;

public class Session {

    private static Session session;
    private String username;
    private String password;
    private Connection connectionDB;

    private Session() {

    }

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