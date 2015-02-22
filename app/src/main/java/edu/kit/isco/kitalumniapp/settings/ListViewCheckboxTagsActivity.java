package edu.kit.isco.kitalumniapp.settings;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.CheckboxTagAdapter;


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
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag(tagsStringArray[0]));
        tagList.add(new Tag(tagsStringArray[1]));
        tagList.add(new Tag(tagsStringArray[2]));
        tagList.add(new Tag(tagsStringArray[3]));
        tagList.add(new Tag(tagsStringArray[4]));
        tagList.add(new Tag(tagsStringArray[5]));
        tagList.add(new Tag(tagsStringArray[6]));
        tagList.add(new Tag(tagsStringArray[7]));
        tagList.add(new Tag(tagsStringArray[8]));
        tagList.add(new Tag(tagsStringArray[9]));
        tagList.add(new Tag(tagsStringArray[10]));
        tagList.add(new Tag(tagsStringArray[11]));
        tagList.add(new Tag(tagsStringArray[12]));
        tagList.add(new Tag(tagsStringArray[13]));
        tagList.add(new Tag(tagsStringArray[14]));

        //create an ArrayAdaptar from the String Array
        dataAdapter = new CheckboxTagAdapter(this, tagList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }
}