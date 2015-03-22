package edu.kit.isco.kitalumniapp;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;

import edu.kit.isco.kitalumniapp.fragments.ContactFragment;
import edu.kit.isco.kitalumniapp.fragments.OverViewFragment;

/**
 * Created by Yannick on 22.03.15 | KW 12.
 */
public class ContactFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    ContactFragment contactFragment;

    public ContactFragmentTest() {
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
        contactFragment = new ContactFragment();
        transaction.add(R.id.container, contactFragment).commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(contactFragment);
    }
}
