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

    /**
     * SQL query for creating the table
     * @return  SQL query
     */
    public static String createSQL() {
        return "CREATE TABLE " + TABLE_NAME + "("
                + JOB_ID + " INTEGER PRIMARY KEY,"
                + TAG_ID + " INTEGER PRIMARY KEY)";
    }

    /**
     * SQL query for dropping the table
     * @return SQL query
     */
    public static String dropSQL() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
