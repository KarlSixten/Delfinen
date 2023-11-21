package domain;

import data.Database;

public class Controller {
    private Database database = new Database();
    public String getAllMemberNames() {
        return database.getAllMemberNames();
    }
}
