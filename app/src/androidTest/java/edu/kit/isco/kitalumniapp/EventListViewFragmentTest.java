package edu.kit.isco.kitalumniapp;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.fragments.EventListViewFragment;

/**
 * Created by Kristina on 21.3.2015 г..
 */
public class EventListViewFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity MainActivity;

    public EventListViewFragmentTest(){
        super(MainActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }
    private void startFragment(Fragment fragment) {
        FragmentTransaction transaction = MainActivity.getFragmentManager().beginTransaction();
        transaction.add(R.id.activity_test_main, fragment).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();

        }
    public void testFragment(){
        EventListViewFragment eventFragment = new EventListViewFragment(){

        };

    }
}

