package edu.kit.isco.kitalumniapp.dbServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.kit.isco.kitalumniapp.dbObjects.*;

/**
 * Created by Andre on 02.02.2015.
 */
public class DBHandlerClient extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "DatabaseClient.db";
    private static final String LOG = "DBHandlerClient";
    public static final int NEWS_IN_DB = 30;
    public static final int JOBS_IN_DB = 30;
    private Context context;

    public DBHandlerClient(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        context.deleteDatabase("test_DatabaseClient.db");
        context.deleteDatabase("test2_DatabaseClient.db");
        context.deleteDatabase("test3_DatabaseClient.db");
        db.execSQL(NewsTable.createSQL());
        db.execSQL(EventTable.createSQL());
        db.execSQL(JobTable.createSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NewsTable.dropSQL());
        db.execSQL(EventTable.dropSQL());
        db.execSQL(JobTable.dropSQL());
        db.execSQL("DROP TABLE IF EXISTS job_tag;");
        db.execSQL("DROP TABLE IF EXISTS tag;");

        onCreate(db);
    }

    /**
     *
     * @return List of all events in the databse
     */
    public List<DataAccessEvent> getAllEvents() {
        List<DataAccessEvent> events = new ArrayList<DataAccessEvent>();
        String selectQuery = "SELECT * FROM " + EventTable.TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        try {
            if (c.moveToFirst()) {
                do {
                    DataAccessEvent ev = new DataAccessEvent();
                    ev.setId(c.getLong(c.getColumnIndex(EventTable.ID)));
                    ev.setTitle(c.getString(c.getColumnIndex(EventTable.TITLE)));
                    ev.setShortInfo(c.getString(c.getColumnIndex(EventTable.SHORT_INFO)));
                    ev.setText(c.getString(c.getColumnIndex(EventTable.FULL_TEXT)));
                    ev.setUrl(c.getString(c.getColumnIndex(EventTable.URL)));
                    ev.setDate(c.getString(c.getColumnIndex(EventTable.DATE)));

                    events.add(ev);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        return events;
    }

    /**
     *
     * @return List of all jobs in the database
     */
    public List<DataAccessJob> getAllJobs() {
        List<DataAccessJob> jobs = new ArrayList<DataAccessJob>();
        String selectQuery = "SELECT * FROM " + JobTable.TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        try {
            if (c.moveToFirst()) {
                do {
                    DataAccessJob job = new DataAccessJob();
                    job.setId(c.getLong(c.getColumnIndex(JobTable.ID)));
                    job.setTitle(c.getString(c.getColumnIndex(JobTable.TITLE)));
                    job.setShortDescription(c.getString(c.getColumnIndex(JobTable.SHORT_INFO)));
                    job.setAllText(c.getString(c.getColumnIndex(JobTable.FULL_TEXT)));
                    job.setUrl(c.getString(c.getColumnIndex(JobTable.URL)));

                    jobs.add(job);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return jobs;
    }

    /**
     *
     * @return List of all news in the database
     */
    public List<DataAccessNews> getAllNews() {
        List<DataAccessNews> news = new ArrayList<DataAccessNews>();
        String selectQuery = "SELECT * FROM " + NewsTable.TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        try {
            if (c.moveToFirst()) {
                do {
                    DataAccessNews n = new DataAccessNews();
                    n.setId(c.getLong(c.getColumnIndex(NewsTable.ID)));
                    n.setTitle(c.getString(c.getColumnIndex(NewsTable.TITLE)));
                    n.setShortDescription(c.getString(c.getColumnIndex(NewsTable.SHORT_INFO)));
                    n.setText(c.getString(c.getColumnIndex(NewsTable.FULL_TEXT)));
                    n.setUrl(c.getString(c.getColumnIndex(NewsTable.URL)));
                    n.setDate(c.getString(c.getColumnIndex(NewsTable.DATE)));
                    n.setImageUrl(c.getString(c.getColumnIndex(NewsTable.IMAGE_URL)));
                    news.add(n);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        return news;
    }

    /**
     * Inserts new news into the database
     * @param news List of news to be inserted
     */
    public void updateNews(List<DataAccessNews> news) {
        assert news != null;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        clearNews();

        try {
            for (int i = 0; i < news.size() && i < NEWS_IN_DB; i++) {
                DataAccessNews n = news.get(i);
                long id = db.insert(NewsTable.TABLE_NAME, null, n.toContentValues());
                n.setId(id);
            }
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    /**
     * Inserts new jobs into the database
     * @param jobs List of jobs to be inserted
     */
    public void updateJobs(List<DataAccessJob> jobs) {
        assert jobs != null;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        clearJobs();

        try {
            for (int i = 0; i < jobs.size() && i < JOBS_IN_DB; i++) {
                DataAccessJob j = jobs.get(i);
                long id = db.insert(JobTable.TABLE_NAME, null, j.toContentValues());
                j.setId(id);
            }
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    /**
     * Inserts new events into the database
     * @param events List of news to be inserted
     */
    public void updateEvents(List<DataAccessEvent> events) {
        assert events != null;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        clearEvents();

        try {
            for (DataAccessEvent e : events) {
                long id = db.insert(EventTable.TABLE_NAME, null, e.toContentValues());
                e.setId(id);
            }
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    /**
     * Deletes all jobs from the database
     */
    private void clearJobs() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        try {
            db.execSQL("delete from " + JobTable.TABLE_NAME);
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    /**
     * Deletes all news from the database
     */
    private void clearNews() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        try {
            db.execSQL("delete from " + NewsTable.TABLE_NAME);
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    /**
     * deletes all events from the database
     */
    private void clearEvents() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        try {
            db.execSQL("delete from " + EventTable.TABLE_NAME);
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    /*public List<DataAccessJob> getJobsByTag(DataAccessTag tag) {
        List<DataAccessJob> jobs = new ArrayList<DataAccessJob>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String select = "SELECT * FROM "+ JOB_TABLE + " jt, "
                + TAG_TABLE + " tt, " + JOB_TAG_TABLE + " jtt WHERE tt."
                + TagTable.NAME + " = '" + tag.getName() + "'" + " AND tt." + TagTable.ID
                + " = " + "jtt." + JobTagTable.TAG_ID + " AND jt." + JobTable.ID + " = "
                + "jtt." + JobTagTable.JOB_ID;

        Log.e(LOG, select);
        Cursor c = db.rawQuery(select, null);

        try {
            if(c.moveToFirst()) {
                do {
                    DataAccessJob job = new DataAccessJob();
                    job.setId(c.getLong(c.getColumnIndex(JobTable.ID)));
                    job.setTitle(c.getString(c.getColumnIndex(JobTable.TITLE)));
                    job.setShortDescription(c.getString(c.getColumnIndex(JobTable.SHORT_INFO)));
                    job.setAllText(c.getString(c.getColumnIndex(JobTable.FULL_TEXT)));
                    job.setUrl(c.getString(c.getColumnIndex(JobTable.URL)));
                    job.setTags(getJobTags(c.getLong(c.getColumnIndex(JobTable.ID))));
                    job.setStar(c.getInt(c.getColumnIndex(JobTable.STAR)) != 0);

                    jobs.add(job);
                } while (c.moveToNext());
            }
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }

        return jobs;
    }*/

    /**
     * Returns a list of news of a certain length
     * @param x number of news that should be returned
     * @return list of news with length x
     */
    public List<DataAccessNews> getXnews(int x) {
        List<DataAccessNews> news = new ArrayList<DataAccessNews>();
        String selectQuery = "SELECT * FROM " + NewsTable.TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        try {
            if (c.moveToLast()) {
                int i = 0;
                do {
                    DataAccessNews n = new DataAccessNews();
                    n.setId(c.getLong(c.getColumnIndex(NewsTable.ID)));
                    n.setTitle(c.getString(c.getColumnIndex(NewsTable.TITLE)));
                    n.setShortDescription(c.getString(c.getColumnIndex(NewsTable.SHORT_INFO)));
                    n.setText(c.getString(c.getColumnIndex(NewsTable.FULL_TEXT)));
                    n.setUrl(c.getString(c.getColumnIndex(NewsTable.URL)));
                    n.setDate(c.getString(c.getColumnIndex(NewsTable.DATE)));
                    n.setImageUrl(c.getString(c.getColumnIndex(NewsTable.IMAGE_URL)));
                    news.add(n);
                    i++;
                } while (i < x && c.moveToPrevious());
            }

        } finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        return news;
    }

    /**
     * Returns a list of events of a certain length
     * @param x number of events that should be returned
     * @return list of events with length x
     */
    public List<DataAccessEvent> getXevents (int x) {
        List<DataAccessEvent> events = new ArrayList<DataAccessEvent>();
        String selectQuery = "SELECT * FROM " + EventTable.TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        try {
            if (c.moveToLast()) {
                int i = 0;
                do {
                    if (Long.parseLong(c.getString(c.getColumnIndex(EventTable.DATE))) > System.currentTimeMillis()){
                    DataAccessEvent ev = new DataAccessEvent();
                    ev.setId(c.getLong(c.getColumnIndex(EventTable.ID)));
                    ev.setTitle(c.getString(c.getColumnIndex(EventTable.TITLE)));
                    ev.setShortInfo(c.getString(c.getColumnIndex(EventTable.SHORT_INFO)));
                    ev.setText(c.getString(c.getColumnIndex(EventTable.FULL_TEXT)));
                    ev.setUrl(c.getString(c.getColumnIndex(EventTable.URL)));
                    ev.setDate(c.getString(c.getColumnIndex(EventTable.DATE)));

                    events.add(ev);
                    i++;
                    }
                } while (c.moveToPrevious() && i < x);
            }
        } finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        return events;
    }

    /**
     * Returns a list of jobs of a certain length
     * @param x number of jobs that should be returned
     * @return list of jobs with length x
     */
    public List<DataAccessJob> getXjobs (int x) {
        List<DataAccessJob> jobs = new ArrayList<DataAccessJob>();
        String selectQuery = "SELECT * FROM " + JobTable.TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        try {
            if (c.moveToLast()) {
                int i = 0;
                do {
                    DataAccessJob job = new DataAccessJob();
                    job.setId(c.getLong(c.getColumnIndex(JobTable.ID)));
                    job.setTitle(c.getString(c.getColumnIndex(JobTable.TITLE)));
                    job.setShortDescription(c.getString(c.getColumnIndex(JobTable.SHORT_INFO)));
                    job.setAllText(c.getString(c.getColumnIndex(JobTable.FULL_TEXT)));
                    job.setUrl(c.getString(c.getColumnIndex(JobTable.URL)));

                    jobs.add(job);
                    i++;
                } while (c.moveToPrevious() && i < x);
            }
        } finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return jobs;
    }

    /**
     * Returns all tags that are affiliated to a certain job
     * @param jobID the ID of the job
     * @return List of tags
     */
    /*private List<DataAccessTag> getJobTags(long jobID) {
        List<DataAccessTag> tags = new ArrayList<DataAccessTag>();
        String select = "SELECT " + JobTagTable.TAG_ID + " FROM " + JOB_TAG_TABLE + " tt WHERE tt."
                + JobTagTable.JOB_ID + " = " + jobID;

        Log.e(LOG, select);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(select, null);

        try {
            if (c.moveToFirst()) {
                do {
                    DataAccessTag t = new DataAccessTag();
                    t.setId(c.getLong(c.getColumnIndex(TagTable.ID)));
                    t.setName(c.getString(c.getColumnIndex(TagTable.NAME)));

                    tags.add(t);
                } while (c.moveToNext());
            }
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }

        return tags;
    }*/

    /**
     * Returns the id that is affiliated with a certain tag
     * @param tag
     * @return the id of the tag
     */
    /*private long getTagID(DataAccessTag tag) {
        long id = -1;
        String select = "SELECT " + TagTable.ID + " FROM " + TAG_TABLE + " tt WHERE tt."
                + TagTable.NAME + " = '" + tag.getName() + "'";
        Log.e(LOG, select);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(select, null);

        try {
            if (c != null) {
                c.moveToFirst();
                id = c.getLong(c.getColumnIndex(TagTable.ID));
            }
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return id;
    }*/

}
