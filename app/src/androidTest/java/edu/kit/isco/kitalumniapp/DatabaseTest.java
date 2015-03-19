package edu.kit.isco.kitalumniapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.widget.ArrayAdapter;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;

import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
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
        //db.execSQL("delete from " + NewsTable.TABLE_NAME);
    }

    public void testUpdateNews() {
        DataAccessNews testNews1 = new DataAccessNews(1, "t", "s", "text", "http://example.com/", "http://example.com/icon.png", "19.03.2015");
        ArrayList<DataAccessNews> news = new ArrayList<>();
        news.add(testNews1);
        new DBHandlerClient(context).updateNews(news);
        ArrayList<DataAccessNews> newsFromDb = (ArrayList<DataAccessNews>) new DBHandlerClient(context).getAllNews();
        Assert.assertEquals(testNews1, newsFromDb.get(0));
    }


    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        super.tearDown();
    }
}
