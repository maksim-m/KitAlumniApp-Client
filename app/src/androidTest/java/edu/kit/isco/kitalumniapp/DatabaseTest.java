package edu.kit.isco.kitalumniapp;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;

/**
 * Created by Max on 09.03.2015.
 */
public class DatabaseTest extends AndroidTestCase {
    private SQLiteDatabase db;

    @Override
    public void setUp() throws Exception {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseManager.initializeInstance(new DBHandlerClient(context));
        db = DatabaseManager.getInstance().openDatabase();
    }

    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        super.tearDown();
    }
}
