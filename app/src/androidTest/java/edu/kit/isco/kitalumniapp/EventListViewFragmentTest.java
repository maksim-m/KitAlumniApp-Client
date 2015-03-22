package edu.kit.isco.kitalumniapp;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import edu.kit.isco.kitalumniapp.fragments.EventListViewFragment;

/**
 * Created by Kristina on 21.3.2015 г..
 */
public class EventListViewFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    private EventListViewFragment eventListViewFragment;
    private ListView eventListView;
    private View detailsListView;

    //Constructor
    public EventListViewFragmentTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        startFragment();
    }
    public void testPerformItemClick(){
    }

    /**
     * Starts a fragment
     */
    private void startFragment() {
        FragmentTransaction transaction = mainActivity.getFragmentManager().beginTransaction();
        eventListViewFragment = new EventListViewFragment();
        transaction.add(R.id.container, eventListViewFragment).commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(eventListViewFragment);
    }
}

