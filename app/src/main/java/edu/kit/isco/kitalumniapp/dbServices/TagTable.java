package edu.kit.isco.kitalumniapp.dbServices;

/**
 * Created by Andre on 02.02.2015.
 */
public class TagTable {

    public static final String ID = "id";
    public static final String NAME = "name";

    public static String createSQL() {
        return "CREATE TABLE tag(" + ID + " INTEGER PRIMARY KEY," + NAME + " Text)";
    }

    public static String dropSQL() {
        return "DROP TABLE IF EXISTS tag";
    }
}
