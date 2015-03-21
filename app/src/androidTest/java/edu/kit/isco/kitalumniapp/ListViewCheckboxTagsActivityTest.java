package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import edu.kit.isco.kitalumniapp.settings.ListViewCheckboxTagsActivity;

/**
 * Created by Kristina on 20.3.2015 г..
 */
public class ListViewCheckboxTagsActivityTest extends ActivityInstrumentationTestCase2<ListViewCheckboxTagsActivity> {
    private ListViewCheckboxTagsActivity listViewCheckboxTagsActivity;
    private TextView chooseTagsTextTest;
    private Button saveButtonTest;

    public ListViewCheckboxTagsActivityTest() {
        super(ListViewCheckboxTagsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        listViewCheckboxTagsActivity = getActivity();
        chooseTagsTextTest=(TextView) listViewCheckboxTagsActivity.findViewById(R.id.chooseTags);
        saveButtonTest=(Button) listViewCheckboxTagsActivity.findViewById(R.id.saveButton);
    }
    /*Test that verify if the test fixture has been set up correctly.*/
    public void testPreconditions() {
        assertNotNull("listViewCheckboxTagsActivity is null",listViewCheckboxTagsActivity);
        assertNotNull("chooseTagsTextTest is null", chooseTagsTextTest);
        assertNotNull("saveButtonText is null", listViewCheckboxTagsActivity);
    }

    /*Test that check if  the TextView of Tags has the correct label text. */
    public void testChooseTagsText(){
        final String expected = listViewCheckboxTagsActivity.getString(R.string.choose_tags_activity_text);
        final String actual =chooseTagsTextTest.getText().toString();
        assertEquals(expected, actual);
    }

    /*Test that verify if the saveButton is displayed correctly in  ListViewCheckboxTagsActivity.*/
    public void testClickSaveButton_layout(){
        final View decorView = listViewCheckboxTagsActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, saveButtonTest);
        final ViewGroup.LayoutParams layoutParams = saveButtonTest.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

}
