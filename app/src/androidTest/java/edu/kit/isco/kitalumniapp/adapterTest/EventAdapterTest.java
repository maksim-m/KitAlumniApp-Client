package edu.kit.isco.kitalumniapp.adapterTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.view.View;

import edu.kit.isco.kitalumniapp.adapter.EventAdapter;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;

/**
 * Created by Yannick on 23.03.15 | KW 13.
 */
public class EventAdapterTest extends AndroidTestCase {
    EventAdapter testClass;
    private SQLiteDatabase db;
    private Context context;


    @Override
    public void setUp () throws Exception{
        this.context = new RenamingDelegatingContext(getContext(), "test3_");
        DatabaseManager.initializeInstance(new DBHandlerClient(context));
        db = DatabaseManager.getInstance().openDatabase();
        testClass = new EventAdapter(context, 0);
        super.setUp();
    }

    public void testLoadJobs() {
        DatabaseManager.initializeInstance(new DBHandlerClient(context));
        db = DatabaseManager.getInstance().openDatabase();
        testClass.update();
    }

    public void testGetViewTest() {
        testClass.update();
        int i = testClass.getCount();
        if (i != 0) {
            View view = testClass.getView(i-1, null, null);
            assertNotNull(view);
        }

    }

    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        context.deleteDatabase("test3_Database_Client");
        super.tearDown();
    }
}
