package edu.kit.isco.kitalumniapp.activityTest;

import android.test.ActivityInstrumentationTestCase2;
import android.webkit.WebView;

import com.robotium.solo.Solo;

import edu.kit.isco.kitalumniapp.fragments.EventDetailsViewActivity;

/**
 *
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class EventDetailsViewActivityTest extends ActivityInstrumentationTestCase2<EventDetailsViewActivity> {
    EventDetailsViewActivity eventDetailsViewActivity;
    private Solo solo;

    //Constructor
    public EventDetailsViewActivityTest () {
        super(EventDetailsViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventDetailsViewActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    /**
     * Tests if Settings starts after pressing ActionBarItem Settings
     */
    public void testSettings() {
        solo.sendKey(Solo.MENU);
        solo.clickOnMenuItem("Settings");
        assertTrue(solo.waitForActivity("SettingsActivity", 5000));
    }

    /**
     * Tests if the activity has been closed after pressing
     * ActionBarHomeButton
     */
    public void testHomeUp() {
        solo.clickOnActionBarHomeButton();
        assertFalse(solo.getCurrentActivity() != eventDetailsViewActivity);
    }

    public void testPreConditions() {
        assertNotNull(eventDetailsViewActivity);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
