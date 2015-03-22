package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.robotium.solo.Solo;

import edu.kit.isco.kitalumniapp.fragments.NavigationDrawerFragment;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class NavigationDrawerTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    NavigationDrawerFragment navigationDrawerFragment;
    private Solo solo;

    //Constructor
    public NavigationDrawerTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
        navigationDrawerFragment = (NavigationDrawerFragment)
                mainActivity.getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(navigationDrawerFragment);
    }
    public void testSelectItem(){
        solo.clickInList(0);
        assertTrue(solo.waitForText("Overview/Home"));

    }
}
