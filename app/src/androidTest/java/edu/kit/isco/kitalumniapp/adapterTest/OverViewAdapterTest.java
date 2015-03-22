package edu.kit.isco.kitalumniapp.adapterTest;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import edu.kit.isco.kitalumniapp.OverviewListItem;
import edu.kit.isco.kitalumniapp.adapter.OverviewAdapter;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;

/**
 * Created by andla_000 on 21.03.2015.
 */
public class OverViewAdapterTest extends AndroidTestCase {
    OverviewAdapter testClass;
    private SQLiteDatabase db;
    private Context context;


    @Override
    public void setUp () throws Exception{
        this.context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseManager.initializeInstance(new DBHandlerClient(context));
        db = DatabaseManager.getInstance().openDatabase();
        testClass = new OverviewAdapter(getContext(), 0);
        super.setUp();
    }

    public void testGetItemViewType () {
        testClass.addItem(new OverviewListItem("Test Header", 3));
        testClass.addItem(new OverviewListItem(new DataAccessNews(), 0));
        testClass.addItem(new OverviewListItem(new DataAccessNews(), 5));
        assertEquals(3, testClass.getItemViewType(0));
        assertEquals(0, testClass.getItemViewType(1));
        assertEquals(-1, testClass.getItemViewType(2));
    }

    public void testAddItem () {
        DataAccessNews testNews = new DataAccessNews();
        testClass.addItem(new OverviewListItem(testNews, 0));
        assertEquals(testNews, testClass.getItem(0));

        try {
            testClass.addItem(new OverviewListItem(4, 3));
        } catch (IllegalArgumentException e) {}

    }

    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        super.tearDown();
    }
}
