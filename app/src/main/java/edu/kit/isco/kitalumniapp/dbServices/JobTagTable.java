package edu.kit.isco.kitalumniapp.dbServices;

/**
 * Created by Andre on 02.02.2015.
 *
 * Represents the connection of jobs and tags within the database
 */
public class JobTagTable {

    private static final String TABLE_NAME = "job_tag";
    //Column names
    public static final String JOB_ID = "job_id";
    public static final String TAG_ID = "tag_id";
    public static final String ID = "id";

    /**
     * SQL query for creating the table
     * @return  SQL query
     */
    public static String createSQL() {
        return "CREATE TABLE " + TABLE_NAME + "("
                    + ID + " INTEGER PRIMARY KEY, "
                    + JOB_ID + " INTEGER REFERENCES " + JobTable.TABLE_NAME + "(" + JobTable.ID +  "), "
                    + TAG_ID + " INTEGER REFERENCES " + TagTable.TABLE_NAME + "(" + TagTable.ID + ")"
                + ");";
    }

    /**
     * SQL query for dropping the table
     * @return SQL query
     */
    public static String dropSQL() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
