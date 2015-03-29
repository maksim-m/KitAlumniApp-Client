package edu.kit.isco.kitalumniapp.settings;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import edu.kit.isco.kitalumniapp.R;


public class SearchActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        //Get the intent, verify the action and get the query
        handleIntent(getIntent());
    }

    /**
     * Prüft, ob einen passenden Intent übergeben war. Falls ja, igbt er ihn weiter.
     * @param intent intent
     */
    private void handleIntent (Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }

    /**
     * Startet eine ACTION__WEB_SEARCH mit dem String query, mit der Begrenzung, dass diese query nur in der Webseite kit.edu und deren Subsaiten gesucht wird.
     * @param query gesuchter String
     */
    private void doMySearch(String query) {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "site:kit.edu " + query);
            startActivity(intent);
        } catch (Exception e) {
            Log.d(e.toString(), e.toString());
        }
    }
}
