package edu.kit.isco.kitalumniapp.dbServices;

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

    public List<DataAccessEvent> getAllEvents() {
        List<DataAccessEvent> events = new ArrayList<DataAccessEvent>();
        String selectQuery = "SELECT * FROM event";

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

    public List<DataAccessJob> getAllJobs() {
        List<DataAccessJob> jobs = new ArrayList<DataAccessJob>();
        String selectQuery = "SELECT * FROM job";

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

    public List<DataAccessNews> getAllNews() {
        List<DataAccessNews> news = new ArrayList<DataAccessNews>();
        String selectQuery = "SELECT * FROM news";

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

    private void clearJobs() {

    }


}
