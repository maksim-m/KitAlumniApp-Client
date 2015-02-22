package edu.kit.isco.kitalumniapp.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.CheckboxTagAdapter;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessTag;


public class ListViewCheckboxTagsActivity extends Activity {

    CheckboxTagAdapter dataAdapter = null;
    private String[] tagsStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        //Generate list View from ArrayList
        displayListView();
    }

    private void displayListView() {
        tagsStringArray = getResources().getStringArray(R.array.tags);

        //Array list of tags
        ArrayList<DataAccessTag> tagList = new ArrayList<>();
        tagList.add(new DataAccessTag(tagsStringArray[0]));
        tagList.add(new DataAccessTag(tagsStringArray[1]));
        tagList.add(new DataAccessTag(tagsStringArray[2]));
        tagList.add(new DataAccessTag(tagsStringArray[3]));
        tagList.add(new DataAccessTag(tagsStringArray[4]));
        tagList.add(new DataAccessTag(tagsStringArray[5]));
        tagList.add(new DataAccessTag(tagsStringArray[6]));
        tagList.add(new DataAccessTag(tagsStringArray[7]));
        tagList.add(new DataAccessTag(tagsStringArray[8]));
        tagList.add(new DataAccessTag(tagsStringArray[9]));
        tagList.add(new DataAccessTag(tagsStringArray[10]));
        tagList.add(new DataAccessTag(tagsStringArray[11]));
        tagList.add(new DataAccessTag(tagsStringArray[12]));

        //create an ArrayAdaptar from the String Array
        dataAdapter = new CheckboxTagAdapter(this, tagList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }
}