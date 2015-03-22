package edu.kit.isco.kitalumniapp;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.fragments.KitNaviFragment;

/**
 * Created by Stelian Stoev on 22.3.2015 г..
 */
public class KitNaviFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    KitNaviFragment kitNaviFragment;

    //Constructor
    public KitNaviFragmentTest() {
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
    private void startFragment() {
        FragmentTransaction transaction = mainActivity.getFragmentManager().beginTransaction();
        kitNaviFragment = new KitNaviFragment();
        transaction.add(R.id.container, kitNaviFragment).commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(kitNaviFragment);
    }
}
