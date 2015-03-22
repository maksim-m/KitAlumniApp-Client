package edu.kit.isco.kitalumniapp;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import edu.kit.isco.kitalumniapp.fragments.NewsDetailsViewActivity;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class NewsDetailsViewActivityTest extends ActivityInstrumentationTestCase2<NewsDetailsViewActivity> {
    NewsDetailsViewActivity newsDetailsViewActivity;
    private Solo solo;
    //Constructor
    public NewsDetailsViewActivityTest() {
        super(NewsDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        newsDetailsViewActivity = getActivity();
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
        assertFalse(solo.getCurrentActivity() != newsDetailsViewActivity);
    }

    public void testPreConditions() {
        assertNotNull(newsDetailsViewActivity);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
