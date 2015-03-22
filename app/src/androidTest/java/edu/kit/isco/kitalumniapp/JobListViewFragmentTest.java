package edu.kit.isco.kitalumniapp;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import edu.kit.isco.kitalumniapp.fragments.JobsListViewFragment;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class JobListViewFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    JobsListViewFragment jobsListViewFragment;

    //Constructor
    public JobListViewFragmentTest () {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        startFragment();
    }

    /**
     * Starts a fragment
     */
    public void startFragment () {
        FragmentTransaction fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        jobsListViewFragment = new JobsListViewFragment();
        fragmentTransaction.replace(R.id.container, jobsListViewFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(jobsListViewFragment);
    }
}
