package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import edu.kit.isco.kitalumniapp.settings.ListViewCheckboxTagsActivity;

/**
 * Created by Kristina on 20.3.2015 г..
 */
public class ListViewCheckboxTagsActivityTest extends ActivityInstrumentationTestCase2<ListViewCheckboxTagsActivity> {
    private ListViewCheckboxTagsActivity listViewCheckboxTagsActivity;
    private TextView chooseTagsTextTest;

    public ListViewCheckboxTagsActivityTest() {
        super(ListViewCheckboxTagsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        listViewCheckboxTagsActivity = getActivity();
        chooseTagsTextTest=(TextView) listViewCheckboxTagsActivity.findViewById(R.id.chooseTags);
    }
    public void testPreconditions() {
        assertNotNull("listViewCheckboxTagsActivity is null",listViewCheckboxTagsActivity);
        assertNotNull("chooseTagsTextTest is null", chooseTagsTextTest);
    }

    public void testChooseTagsText(){
        final String expected = listViewCheckboxTagsActivity.getString(R.string.choose_tags_activity_text);
        final String actual =chooseTagsTextTest.getText().toString();
        assertEquals(expected, actual);
    }

}
