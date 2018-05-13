package app;

import app.Databases.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabasesList {

    private ObservableList<Database> databases = FXCollections.observableArrayList();

    public DatabasesList(Database ... db) {
        for (int x = 0; x < db.length; x++) {
            if (db[x] != null) {
                databases.add(db[x]);
            }
        }
    }

    public ObservableList<Database> getList() {
        return databases;
    }

    public void addDatabase(Database db) {
        databases.add(db);
    }
}