package edu.kit.isco.kitalumniapp;

import android.content.Intent;


import edu.kit.isco.kitalumniapp.settings.SettingsActivity;

/**
 * Created by Kristina on 22.3.2015 г..
 */
public class SettingsActivityTest extends android.test.ActivityUnitTestCase<SettingsActivity> {
    SettingsActivity settingsActivity;

    public SettingsActivityTest(){super(SettingsActivity.class); }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                SettingsActivity.class);
        try {
            startActivity(intent, null, null);
        }catch (IllegalStateException e) { }
        settingsActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(settingsActivity);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
