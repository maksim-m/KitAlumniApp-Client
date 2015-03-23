package edu.kit.isco.kitalumniapp.activityTest;


import android.content.Intent;

import edu.kit.isco.kitalumniapp.settings.SearchActivity;

/**
 * Created by Kristina on 22.3.2015 г..
 */
public class SearchActivityTest extends android.test.ActivityUnitTestCase<SearchActivity> {
    SearchActivity searchActivity;

    SearchActivityTest() {
        super(SearchActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                SearchActivity.class);
        try {
            startActivity(intent, null, null);
        } catch (IllegalStateException e) {
        }
        searchActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(searchActivity);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
