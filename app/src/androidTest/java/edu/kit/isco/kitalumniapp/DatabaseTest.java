package edu.kit.isco.kitalumniapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.widget.ArrayAdapter;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessTag;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;
import edu.kit.isco.kitalumniapp.dbServices.NewsTable;

/**
 * Created by Max on 09.03.2015.
 */
public class DatabaseTest extends AndroidTestCase {
    private Context context;
    private SQLiteDatabase db;

    @Override
    public void setUp() throws Exception {
        this.context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseManager.initializeInstance(new DBHandlerClient(context));
        db = DatabaseManager.getInstance().openDatabase();
    }

    public void testUpdateNews() {
        DataAccessNews testNews1 = new DataAccessNews(1, "t", "s", "text", "http://example.com/", "http://example.com/icon.png", "19.03.2015");
        DataAccessNews testNews2 = new DataAccessNews(2, "tt", "ss", "text2", "http://example.edu/", "http://example.edu/icon.png", "20.03.2015");
        ArrayList<DataAccessNews> news = new ArrayList<>();
        news.add(testNews1);
        news.add(testNews2);
        new DBHandlerClient(context).updateNews(news);
        ArrayList<DataAccessNews> newsFromDb = (ArrayList<DataAccessNews>) new DBHandlerClient(context).getAllNews();
        Assert.assertEquals(news, newsFromDb);
    }

    public void testUpdateEvents() {
        final DataAccessEvent testEvent1 = new DataAccessEvent("e1", "s1", "<html><body>hello!</body></html>", "http://example.com/", Calendar.getInstance());
        testEvent1.setId(1);
        final DataAccessEvent testEvent2 = new DataAccessEvent("e2", "s2", "<html><body>hello2!</body></html>", "http://example.de/", Calendar.getInstance());
        testEvent2.setId(2);
        List<DataAccessEvent> events = new ArrayList<DataAccessEvent>() {{
            add(testEvent1);
            add(testEvent2);
        }};
        new DBHandlerClient(context).updateEvents(events);
        ArrayList<DataAccessEvent> eventsFromDb = (ArrayList<DataAccessEvent>) new DBHandlerClient(context).getAllEvents();
        Assert.assertEquals(events, eventsFromDb);
    }

    public void testUpdateJobs() {
        ArrayList<DataAccessTag> tagsSet1 = new ArrayList<DataAccessTag>() {{
            add(DataAccessTag.ENGINEER);
            add(DataAccessTag.PROFESSOR);
        }};
        ArrayList<DataAccessTag> tagsSet2 = new ArrayList<DataAccessTag>() {{
            add(DataAccessTag.ADMINISTRATION);
            add(DataAccessTag.SCIENTIST);
            add(DataAccessTag.DOCTORAND);
        }};

        DataAccessJob testJob1 = new DataAccessJob();
    }

    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        super.tearDown();
    }
}
