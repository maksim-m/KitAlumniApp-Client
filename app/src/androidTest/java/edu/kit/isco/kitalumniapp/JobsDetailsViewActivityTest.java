package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.fragments.JobsDetailsViewActivity;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class JobsDetailsViewActivityTest extends ActivityInstrumentationTestCase2<JobsDetailsViewActivity> {
    JobsDetailsViewActivity jobsDetailsViewActivity;

    public JobsDetailsViewActivityTest () {
        super(JobsDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jobsDetailsViewActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(jobsDetailsViewActivity);
    }
}
