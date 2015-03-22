package edu.kit.isco.kitalumniapp.adapterTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import edu.kit.isco.kitalumniapp.OverviewListItem;
import edu.kit.isco.kitalumniapp.adapter.JobsAdapter;
import edu.kit.isco.kitalumniapp.adapter.OverviewAdapter;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;
import edu.kit.isco.kitalumniapp.dbServices.DatabaseManager;

/**
 * Created by Yannick on 22.03.15 | KW 12.
 */
public class JobsAdapterTest extends AndroidTestCase {
    JobsAdapter testClass;



    @Override
    public void setUp () throws Exception{
        testClass = new JobsAdapter(getContext(), 0);
        super.setUp();
    }



    @Override
    public void tearDown() throws Exception {
        DatabaseManager.getInstance().closeDatabase();
        super.tearDown();
    }
}
