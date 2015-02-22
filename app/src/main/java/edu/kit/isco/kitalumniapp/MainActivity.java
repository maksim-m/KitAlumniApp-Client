package edu.kit.isco.kitalumniapp;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;
import edu.kit.isco.kitalumniapp.fragments.ContactFragment;
import edu.kit.isco.kitalumniapp.fragments.EventListViewFragment;
import edu.kit.isco.kitalumniapp.fragments.JobsListViewFragment;
import edu.kit.isco.kitalumniapp.fragments.KitAtAGlanceFragment;
import edu.kit.isco.kitalumniapp.fragments.KitNaviFragment;
import edu.kit.isco.kitalumniapp.fragments.NavigationDrawerFragment;
import edu.kit.isco.kitalumniapp.fragments.NewsListViewFragment;
import edu.kit.isco.kitalumniapp.fragments.OverViewFragment;
import edu.kit.isco.kitalumniapp.settings.SettingsActivity;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // initialize database manager
        DatabaseManager.initializeInstance(new DBHandlerClient(this));

    }

    // You need to do the Play Services APK check here too.
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new OverViewFragment();
                break;
            case 1:
                fragment = new JobsListViewFragment();
                break;
            case 2:
                fragment = new NewsListViewFragment();
                break;
            case 3:
                fragment = new KitAtAGlanceFragment();
                break;
            case 4:
                fragment = new EventListViewFragment();
                break;
            case 5:
                fragment = new KitNaviFragment();
                break;
            case 6:
                fragment = new ContactFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            onSectionAttached(position + 1);
            setTitle(mTitle);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void onSectionAttached(int number) {
        String[] menuTitles = getResources().getStringArray(R.array.menuTitles);
        switch (number) {
            case 1:
                mTitle = menuTitles[number-1];
                break;
            case 2:
                mTitle = menuTitles[number-1];
                break;
            case 3:
                mTitle = menuTitles[number-1];
                break;
            case 4:
                mTitle = menuTitles[number-1];
                break;
            case 5:
                mTitle = menuTitles[number-1];
                break;
            case 6:
                mTitle = menuTitles[number-1];
                break;
            case 7:
                mTitle = menuTitles[number-1];
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()) );
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchRequested();
                return true;

            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
