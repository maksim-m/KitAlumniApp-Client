package edu.kit.isco.kitalumniapp.dbServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.kit.isco.kitalumniapp.dbObjects.*;

/**
 * Created by Andre on 02.02.2015.
 */
public class DBHandlerClient extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database_Client";
    private static final String LOG = "DBHandlerClient";


    //Table Names
    private static final String JOB_TABLE = "job";
    private static final String EVENT_TABLE = "event";
    private static final String NEWS_TABLE = "news";

    public DBHandlerClient(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventTable.createSQL());
        db.execSQL(TagTable.createSQL());
        db.execSQL(JobTable.createSQL());
        db.execSQL(NewsTable.createSQL());
        db.execSQL(JobTagTable.createSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventTable.dropSQL());
        db.execSQL(TagTable.dropSQL());
        db.execSQL(JobTable.dropSQL());
        db.execSQL(NewsTable.dropSQL());
        db.execSQL(JobTagTable.dropSQL());

        onCreate(db);
    }

    /**
     *
     * @return List of all events in the databse
     */
    public List<DataAccessEvent> getAllEvents() {
        List<DataAccessEvent> events = new ArrayList<DataAccessEvent>();
        String selectQuery = "SELECT * FROM " + EVENT_TABLE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                DataAccessEvent ev = new DataAccessEvent();
                ev.setId(c.getLong(c.getColumnIndex(EventTable.ID)));
                ev.setTitle(c.getString(c.getColumnIndex(EventTable.TITLE)));
                ev.setShort_info(c.getString(c.getColumnIndex(EventTable.SHORT_INFO)));
                ev.setText(c.getString(c.getColumnIndex(EventTable.TEXT)));
                ev.setUrl(c.getString(c.getColumnIndex(EventTable.URL)));
                ev.setDate(c.getString(c.getColumnIndex(EventTable.DATE)));

                events.add(ev);
            } while (c.moveToNext());
        }

        return events;
    }

    /**
     *
     * @return List of all jobs in the database
     */
    public List<DataAccessJob> getAllJobs() {
        List<DataAccessJob> jobs = new ArrayList<DataAccessJob>();
        String selectQuery = "SELECT * FROM " + JOB_TABLE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                DataAccessJob job = new DataAccessJob();
                job.setId(c.getLong(c.getColumnIndex(JobTable.ID)));
                job.setTitle(c.getString(c.getColumnIndex(JobTable.TITLE)));
                job.setShort_info(c.getString(c.getColumnIndex(JobTable.SHORT_INFO)));
                job.setText(c.getString(c.getColumnIndex(JobTable.TEXT)));
                job.setUrl(c.getString(c.getColumnIndex(JobTable.URL)));
                job.setDate(c.getString(c.getColumnIndex(JobTable.DATE)));

                jobs.add(job);
            } while (c.moveToNext());
        }

        return jobs;
    }

    /**
     *
     * @return List of all news in the database
     */
    public List<DataAccessNews> getAllNews() {
        List<DataAccessNews> news = new ArrayList<DataAccessNews>();
        String selectQuery = "SELECT * FROM " + NEWS_TABLE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                DataAccessNews n = new DataAccessNews();
                n.setId(c.getLong(c.getColumnIndex(NewsTable.ID)));
                n.setTitle(c.getString(c.getColumnIndex(NewsTable.TITLE)));
                n.setShort_info(c.getString(c.getColumnIndex(NewsTable.SHORT_INFO)));
                n.setText(c.getString(c.getColumnIndex(NewsTable.TEXT)));
                n.setUrl(c.getString(c.getColumnIndex(NewsTable.URL)));
                n.setDate(c.getString(c.getColumnIndex(NewsTable.DATE)));
                n.setImage_url(c.getString(c.getColumnIndex(NewsTable.IMAGE_URL)));
                news.add(n);
            } while (c.moveToNext());
        }

        return news;
    }

    /**
     * Inserts new news into the database
     * @param news List of news to be inserted
     */
    public void updateNews(List<DataAccessNews> news) {
        assert news != null;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values;

        for (DataAccessNews n : news) {
            values = new ContentValues();
            values.put(NewsTable.TITLE, n.getTitle());
            values.put(NewsTable.SHORT_INFO, n.getShort_info());
            values.put(NewsTable.TEXT, n.getText());
            values.put(NewsTable.URL, n.getUrl());
            values.put(NewsTable.IMAGE_URL, n.getImage_url());
            values.put(NewsTable.DATE, n.getDate());

            long id = db.insert(NEWS_TABLE, null, values);
            n.setId(id);
        }
    }

    /**
     * Inserts new jobs into the database
     * @param jobs List of jobs to be inserted
     */
    public void updateJobs(List<DataAccessJob> jobs) {
        assert jobs != null;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values;

        for (DataAccessJob j : jobs) {
            values = new ContentValues();
            values.put(JobTable.TITLE, j.getTitle());
            values.put(JobTable.SHORT_INFO, j.getShort_info());
            values.put(JobTable.TEXT, j.getText());
            values.put(JobTable.URL, j.getUrl());
            values.put(JobTable.DATE, j.getDate());

            long id = db.insert(JOB_TABLE, null, values);
            j.setId(id);
        }
    }

    /**
     * Inserts new events into the database
     * @param events List of news to be inserted
     */
    public void updateEvents(List<DataAccessEvent> events) {
        assert events != null;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values;

        for (DataAccessEvent e : events) {
            values = new ContentValues();
            values.put(EventTable.TITLE, e.getTitle());
            values.put(EventTable.SHORT_INFO, e.getShort_info());
            values.put(EventTable.TEXT, e.getText());
            values.put(EventTable.URL, e.getUrl());
            values.put(EventTable.DATE, e.getDate());

            long id = db.insert(EVENT_TABLE, null, values);
            e.setId(id);
        }
    }

    /**
     * Deletes all jobs from the database
     */
    private void clearJobs() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(JOB_TABLE, null, null);
    }

    /**
     * Deletes all news from the database
     */
    private void clearNews() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(NEWS_TABLE, null, null);
    }

    /**
     * deletes all events from the database
     */
    private void clearEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(EVENT_TABLE, null, null);
    }


}
