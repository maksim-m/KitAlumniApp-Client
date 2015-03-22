package edu.kit.isco.kitalumniapp;

import android.test.AndroidTestCase;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.adapter.ExpandableListAdapter;

/**
 * Created by Yannick on 21.03.15 | KW 12.
 */
public class ExpandableListAdapterTest extends AndroidTestCase {
    ExpandableListAdapter testAdapter;
    ArrayList<ContactParentItem> current = new ArrayList<ContactParentItem>();


    @Override
    public void setUp() throws Exception {
        testAdapter = new ExpandableListAdapter(getContext(), current);
        assertEquals(0, testAdapter.getGroupCount());
        super.setUp();
    }

    public void testCount() {
        current.clear();
        current.add(new ContactParentItem("Test","Hello", "", null, null));
        current.add(new ContactParentItem("Test2","MyInfo", "1234567", "muster@mann.de", "www.website.com"));
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
