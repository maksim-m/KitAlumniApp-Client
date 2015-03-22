package edu.kit.isco.kitalumniapp;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.fragments.KitNaviFragment;
import edu.kit.isco.kitalumniapp.fragments.OverViewFragment;

/**
 * Created by Yannick on 22.03.15 | KW 12.
 */
public class KitNaviFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    KitNaviFragment kitNaviFragment;

    public KitNaviFragmentTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        startFragment();
    }

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
