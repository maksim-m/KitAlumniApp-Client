package edu.kit.isco.kitalumniapp.fragmentsTest;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.MainActivity;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.fragments.OverViewFragment;

/**
 * Created by Andre on 21.03.2015.
 */
public class OverViewFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    OverViewFragment overViewFragment;

    public OverViewFragmentTest() {
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
        overViewFragment = new OverViewFragment();
        transaction.add(R.id.container, overViewFragment).commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(overViewFragment);
    }
}