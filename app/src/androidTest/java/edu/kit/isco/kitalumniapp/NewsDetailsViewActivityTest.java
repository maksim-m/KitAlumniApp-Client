package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.fragments.NewsDetailsViewActivity;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class NewsDetailsViewActivityTest extends ActivityInstrumentationTestCase2<NewsDetailsViewActivity> {
    NewsDetailsViewActivity newsDetailsViewActivity;

    public NewsDetailsViewActivityTest() {
        super(NewsDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        newsDetailsViewActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(newsDetailsViewActivity);
    }
}
