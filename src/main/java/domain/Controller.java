package domain;

import data.Database;

import java.io.IOException;

public class Controller {
    private Database database;
    public String getAllMemberNames() {
        return database.getAllMemberNames();
    }
    public Controller() throws IOException{
        this.database = new Database();
    }
    public void loadData(){
        database.saveMembers();
    }
}
