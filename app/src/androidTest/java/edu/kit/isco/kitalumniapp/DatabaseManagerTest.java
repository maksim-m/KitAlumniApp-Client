package edu.kit.isco.kitalumniapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;

/**
 * Created by Max on 20.03.2015.
 */
public class DatabaseManagerTest extends AndroidTestCase {
    private Context context;
    private SQLiteDatabase db;

    @Override
    public void setUp() throws Exception {
        this.context = new RenamingDelegatingContext(getContext(), "test_");
    }

    public void testDatabaseNotInitializedException() {
        try {
            DatabaseManager.getInstance().openDatabase();
        } catch (IllegalStateException e) {

        }
    }
}
