package edu.kit.isco.kitalumniapp;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.adapter.ExpandableListAdapter;
import edu.kit.isco.kitalumniapp.adapter.KitAtAGlanceAdapter;

/**
 * Created by Yannick on 21.03.15 | KW 12.
 */
public class ExpandableListAdapterTest extends AndroidTestCase {
    ExpandableListAdapter testAdapter;
    ArrayList<Contact> current = new ArrayList<Contact>();


    @Override
    public void setUp() throws Exception {
        testAdapter = new ExpandableListAdapter(getContext(), current);
        assertEquals(0, testAdapter.getGroupCount());
        super.setUp();
    }

    public void testCount() {
        current.clear();
        current.add(new Contact("Test","Hello", "", null, null));
        current.add(new Contact("Test2","MyInfo", "1234567", "muster@mann.de", "www.website.com"));
        testAdapter = new ExpandableListAdapter(getContext(), current);
        int i = testAdapter.getGroupCount();
        assertEquals(2,i);
        assertEquals(1,testAdapter.getChildrenCount(i-2));
        assertEquals(3,testAdapter.getChildrenCount(i-1));
    }
    

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
