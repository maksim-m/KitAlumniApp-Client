package edu.kit.isco.kitalumniapp.fragmentsTest;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import edu.kit.isco.kitalumniapp.MainActivity;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.fragments.JobsListViewFragment;
import edu.kit.isco.kitalumniapp.fragments.NewsListViewFragment;

/**
 * Created by Stelian Stoev on 21.3.2015 г..
 */
public class NewsListViewFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    NewsListViewFragment newsListViewFragment;

    //Constructor
    public NewsListViewFragmentTest() {
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
    public void startFragment() {
        FragmentTransaction fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        newsListViewFragment = new NewsListViewFragment();
        fragmentTransaction.replace(R.id.container, newsListViewFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void testPreConditions() {
        assertNotNull(mainActivity);
        assertNotNull(newsListViewFragment);
    }
}
