package edu.kit.isco.kitalumniapp.fragmentsTest;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.MainActivity;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.fragments.KitAtAGlanceFragment;

/**
 * Created by Stelian Stoev on 22.3.2015 г..
 */
public class KitAtAGlanceFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    KitAtAGlanceFragment kitAtAGlanceFragment;

    //Constructor
    public KitAtAGlanceFragmentTest() {
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
        kitAtAGlanceFragment = new KitAtAGlanceFragment();
        transaction.add(R.id.container, kitAtAGlanceFragment).commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(kitAtAGlanceFragment);
    }
}
