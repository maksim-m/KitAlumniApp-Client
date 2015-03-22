package edu.kit.isco.kitalumniapp;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.webkit.WebView;

import edu.kit.isco.kitalumniapp.fragments.JobsDetailsViewActivity;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class JobsDetailsViewActivityTest extends android.test.ActivityUnitTestCase<JobsDetailsViewActivity> {
    private JobsDetailsViewActivity jobsDetailsViewActivity;
    private WebView jobsDetailsWebView;

    public JobsDetailsViewActivityTest () {
        super(JobsDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                JobsDetailsViewActivity.class);
        try {
            startActivity(intent, null, null);
        }catch (IllegalStateException e) {

        }
        jobsDetailsViewActivity = getActivity();
    }

    public void testPreConditions() {

        assertNotNull(jobsDetailsViewActivity);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
