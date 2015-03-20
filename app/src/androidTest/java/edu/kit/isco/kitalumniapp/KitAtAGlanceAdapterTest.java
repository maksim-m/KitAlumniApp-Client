package edu.kit.isco.kitalumniapp;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.adapter.KitAtAGlanceAdapter;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;

/**
 * Created by Yannick on 20.03.15 | KW 12.
 */
public class KitAtAGlanceAdapterTest extends AndroidTestCase {
    KitAtAGlanceAdapter testClass;


    @Override
    public void setUp() throws Exception {
        testClass = new KitAtAGlanceAdapter(getContext());
       super.setUp();
    }

    public void testAdd() {
        assertEquals(0,testClass.getCount()); //At init should be empty
        testClass.addPdf("Hello", "Hello.pdf");
        assertEquals(1,testClass.getCount()); // Now should be one
        testClass.addInfoText("MeAgain", "Bliblablub");
        assertEquals(2,testClass.getCount());

    }

    public void testViewTypeCount() {
        assertEquals(2, testClass.getViewTypeCount());
    }

    public void testItemViewCount() {
        int pos = testClass.getCount();
        testClass.addInfoText("Postion pos + 1", "Hello");
        assertEquals(pos+1, testClass.getCount());
        testClass.addPdf("","");
        assertEquals(1,testClass.getItemViewType(pos+1));
    }


    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
