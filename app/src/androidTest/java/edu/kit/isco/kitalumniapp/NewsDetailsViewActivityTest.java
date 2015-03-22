package edu.kit.isco.kitalumniapp;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.fragments.JobsDetailsViewActivity;
import edu.kit.isco.kitalumniapp.fragments.NewsDetailsViewActivity;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class NewsDetailsViewActivityTest extends android.test.ActivityUnitTestCase<NewsDetailsViewActivity> {
    NewsDetailsViewActivity newsDetailsViewActivity;

    public NewsDetailsViewActivityTest() {
        super(NewsDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                NewsDetailsViewActivity.class);
        try {
            startActivity(intent, null, null);
        }catch (IllegalStateException e) {  }
        newsDetailsViewActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(newsDetailsViewActivity);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
