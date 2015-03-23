package edu.kit.isco.kitalumniapp.dbServicesTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.widget.ArrayAdapter;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessTag;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;
import edu.kit.isco.kitalumniapp.dbServices.EventTable;
import edu.kit.isco.kitalumniapp.dbServices.JobTable;
import edu.kit.isco.kitalumniapp.dbServices.NewsTable;

/**
 * Created by Max on 09.03.2015.
 */
public class DatabaseTest extends AndroidTestCase {
    private Context context;
    private SQLiteDatabase db;

    private DataAccessNews testNews1;
    private DataAccessNews testNews2;

    private DataAccessEvent testEvent1;
    private DataAccessEvent testEvent2;

    ArrayList<DataAccessTag> tagsSet1;
    ArrayList<DataAccessTag> tagsSet2;

    DataAccessJob testJob1;
    DataAccessJob testJob2;

    @Override
    public void setUp() throws Exception {
        this.context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseManager.initializeInstance(new DBHandlerClient(context));
        db = DatabaseManager.getInstance().openDatabase();
        testNews1 = new DataAccessNews(1, "t", "s", "text", "http://example.com/", "http://example.com/icon.png", "19.03.2015");
        testNews2 = new DataAccessNews(2, "tt", "ss", "text2", "http://example.edu/", "http://example.edu/icon.png", "20.03.2015");
        testEvent1 = new DataAccessEvent("e1", "s1", "<html><body>hello!</body></html>", "http://example.com/", Calendar.getInstance());
        testEvent1.setId(1);
        testEvent2 = new DataAccessEvent("e2", "s2", "<html><body>hello2!</body></html>", "http://example.de/", Calendar.getInstance());
        testEvent2.setId(2);
        tagsSet1 = new ArrayList<DataAccessTag>() {{
            add(DataAccessTag.ENGINEER);
            add(DataAccessTag.PROFESSOR);
        }};
        tagsSet2 = new ArrayList<DataAccessTag>() {{
            add(DataAccessTag.ADMINISTRATION);
            add(DataAccessTag.SCIENTIST);
            add(DataAccessTag.DOCTORAND);
        }};
        testJob1 = new DataAccessJob(tagsSet1, "Wizard", "Task: kill all dragons in Karlsruhe", "http://example.com/");
        testJob1.setId(1);
        testJob2 = new DataAccessJob(tagsSet2, "Title2", "Description", "http://example.de/");
        testJob2.setId(2);
    }


    public void testUpdateNews() {
        ArrayList<DataAccessNews> news = new ArrayList<>();
        news.add(testNews1);
        news.add(testNews2);
        new DBHandlerClient(context).updateNews(news);
        ArrayList<DataAccessNews> newsFromDb = (ArrayList<DataAccessNews>) new DBHandlerClient(context).getAllNews();
        assertEquals(news, newsFromDb);
        newsFromDb = (ArrayList<DataAccessNews>) new DBHandlerClient(context).getXnews(2);
        Collections.reverse(newsFromDb);
        assertEquals(news, newsFromDb);
        try {
            tearDown();
        } catch (Exception e) {

        }
    }

    public void testUpdateEvents() {
        List<DataAccessEvent> events = new ArrayList<DataAccessEvent>() {{
            add(testEvent1);
            add(testEvent2);
        }};
        new DBHandlerClient(context).updateEvents(events);
        ArrayList<DataAccessEvent> eventsFromDb = (ArrayList<DataAccessEvent>) new DBHandlerClient(context).getAllEvents();
        assertEquals(events, eventsFromDb);
        eventsFromDb = (ArrayList<DataAccessEvent>) new DBHandlerClient(context).getXevents(2);
        Collections.reverse(eventsFromDb);
        assertEquals(events, eventsFromDb);
        try {
            tearDown();
        } catch (Exception e) {

        }
    }

    public void testUpdateJobs() {
        ArrayList<DataAccessJob> jobs = new ArrayList<>();
        jobs.add(testJob1);
        jobs.add(testJob2);
        new DBHandlerClient(context).updateJobs(jobs);
        ArrayList<DataAccessJob> jobsFromDb = (ArrayList<DataAccessJob>) new DBHandlerClient(context).getAllJobs();
        assertEquals(jobs, jobsFromDb);
        jobsFromDb = (ArrayList<DataAccessJob>) new DBHandlerClient(context).getXjobs(2);
        Collections.reverse(jobsFromDb);
        assertEquals(jobs, jobsFromDb);
        try {
            tearDown();
        } catch (Exception e) {

        }
    }

    public void testUpgrade() {
        new DBHandlerClient(context).onUpgrade(db, 1, 2);
        new DBHandlerClient(context).onUpgrade(db, 2, 3);
    }

    public void testCreateTables() {
        NewsTable newsTable = new NewsTable();
        assertNotNull(newsTable);
        EventTable eventTable = new EventTable();
        assertNotNull(eventTable);
        JobTable jobTable = new JobTable();
        assertNotNull(jobTable);

    }

    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        context.deleteDatabase("test_Database_Client");
        super.tearDown();
    }
}
