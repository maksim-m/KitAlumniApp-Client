package edu.kit.isco.kitalumniapp.activityTest;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;


import com.robotium.solo.Solo;

import edu.kit.isco.kitalumniapp.settings.AboutActivity;
import edu.kit.isco.kitalumniapp.settings.ImpressumActivity;
import edu.kit.isco.kitalumniapp.settings.ListViewCheckboxTagsActivity;
import edu.kit.isco.kitalumniapp.settings.SettingsActivity;

/**
 * Created by Kristina on 22.3.2015 г..
 */
public class SettingsActivityTest extends ActivityInstrumentationTestCase2<SettingsActivity> {
    SettingsActivity settingsActivity;
    private Solo solo;

    public SettingsActivityTest(){super(SettingsActivity.class); }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        settingsActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testPreConditions() {
        assertNotNull(settingsActivity);
    }

    public void testImpressum() {
        solo.clickOnText("Impressum");
        solo.assertCurrentActivity("Activity Impressum is not started", ImpressumActivity.class);
    }

    /**
     * Tests if the correct activity was started
     */
    public void testAbout() {
        solo.clickOnText("About");
        solo.assertCurrentActivity("Activity About was not started", AboutActivity.class);
    }

    /**
     * Tests if the correct activity was started
     */
    public void testTags() {
        solo.clickOnText("Tags");
        solo.assertCurrentActivity("Activity ListTagsActivity was not started", ListViewCheckboxTagsActivity.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
