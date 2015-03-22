package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import edu.kit.isco.kitalumniapp.settings.ImpressumActivity;

/**
 * Created by Kristina on 20.3.2015 г..
 */
public class ImpressumActivityTest extends ActivityInstrumentationTestCase2<ImpressumActivity> {
    private ImpressumActivity impressumActivity;
    private TextView providerTitleTextViewTest;
    private TextView providerTextTextViewTest;
    private Solo solo;

    public ImpressumActivityTest() {
        super(ImpressumActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        impressumActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
        providerTitleTextViewTest = (TextView) impressumActivity.findViewById(R.id.providerTitle);
        providerTextTextViewTest = (TextView) impressumActivity.findViewById(R.id.providerText);
    }

    /*Test that verify if the test fixture has been set up correctly.*/
    public void testPreconditions() {
        assertNotNull(impressumActivity);
        assertNotNull(providerTitleTextViewTest);
        assertNotNull(providerTextTextViewTest);
    }

    /*Test that check if  the TextView of Impressum Title has the correct label text. */
    public void testProviderTitleTextView() {
        final String expected = impressumActivity.getString(R.string.impressum_provider_title);
        final String actual = providerTitleTextViewTest.getText().toString();
        assertEquals(expected, actual);
    }
    public  void testProviderTextTextView(){
        final String expected = impressumActivity.getString(R.string.impressum_provider_text);
        final String actual = providerTextTextViewTest.getText().toString();
        assertEquals(expected, actual);
    }

    /**
     * Tests if the activity has been closed after pressing
     * ActionBarHomeButton
     */
    public void testHomeUp() {
        solo.clickOnActionBarHomeButton();
        assertFalse(solo.getCurrentActivity() != impressumActivity);
    }
}
