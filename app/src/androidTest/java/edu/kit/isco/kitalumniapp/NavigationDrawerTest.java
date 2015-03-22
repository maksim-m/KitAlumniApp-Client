package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;
import edu.kit.isco.kitalumniapp.fragments.NavigationDrawerFragment;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class NavigationDrawerTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    NavigationDrawerFragment navigationDrawerFragment;

    //Constructor
    public NavigationDrawerTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        navigationDrawerFragment = (NavigationDrawerFragment)
                mainActivity.getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(navigationDrawerFragment);
    }
}
