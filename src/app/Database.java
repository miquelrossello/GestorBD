package app;

import javafx.beans.property.SimpleStringProperty;

public class Database {

    private SimpleStringProperty name;

    public Database(String name) {
        if (name != null) {
            this.name = new SimpleStringProperty(name);
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
