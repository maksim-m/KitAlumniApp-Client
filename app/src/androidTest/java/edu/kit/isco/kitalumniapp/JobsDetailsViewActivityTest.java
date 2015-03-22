package edu.kit.isco.kitalumniapp;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.webkit.WebView;

import com.robotium.solo.Solo;

import edu.kit.isco.kitalumniapp.fragments.JobsDetailsViewActivity;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class JobsDetailsViewActivityTest extends ActivityInstrumentationTestCase2<JobsDetailsViewActivity> {
    JobsDetailsViewActivity jobsDetailsViewActivity;
    private Solo solo;

    //Constructor
    public JobsDetailsViewActivityTest () {
        super(JobsDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jobsDetailsViewActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    /**
     * Tests if Settings starts after pressing ActionBarItem Settings
     */
    public void testSettings() {
        solo.sendKey(Solo.MENU);
        solo.clickOnMenuItem("Settings");
        assertTrue(solo.waitForActivity("SettingsActivity", 500));
    }

    /**
     * Tests if the activity has been closed after pressing
     * ActionBarHomeButton
     */
    public void testHomeUp() {
        solo.clickOnActionBarHomeButton();
        assertFalse(solo.getCurrentActivity() != jobsDetailsViewActivity);
    }

    public void testPreConditions() {
        assertNotNull(jobsDetailsViewActivity);
    }
}
