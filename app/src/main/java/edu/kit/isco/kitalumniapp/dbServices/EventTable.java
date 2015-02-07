package edu.kit.isco.kitalumniapp.dbServices;

/**
 * Created by Andre on 02.02.2015.
 *
 * Representation of the database table containing events
 */
public class EventTable {

    //Column Names
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String SHORT_INFO = "short_info";
    public static final String TEXT = "text";
    public static final String URL = "url";
    public static final String DATE = "date";

    /**
     * SQL query for creating the table
     * @return  SQL query
     */
    public static String createSQL() {
        return "CREATE TABLE event(" + ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + SHORT_INFO +
                " TEXT," + TEXT + " TEXT," + URL + " TEXT," + DATE + " DATETIME)";
    }

    /**
     * SQL query for dropping the table
     * @return SQL query
     */
    public static String dropSQL() {
        return "DROP TABLE IF EXISTS event";
    }

}
