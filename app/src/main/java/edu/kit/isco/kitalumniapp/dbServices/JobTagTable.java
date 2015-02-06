package edu.kit.isco.kitalumniapp.dbServices;

/**
 * Created by Andre on 02.02.2015.
 */
public class JobTagTable {

    public static final String JOB_ID = "job_id";
    public static final String TAG_ID = "tag_id";

    public static String createSQL() {
        return "CREATE TABLE tag(" + JOB_ID + " INTEGER PRIMARY KEY," + TAG_ID + " INTEGER PRIMARY KEY)";
    }

    public static String dropSQL() {
        return "DROP TABLE IF EXISTS tag";
    }
}
