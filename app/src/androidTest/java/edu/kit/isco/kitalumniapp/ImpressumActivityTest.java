package edu.kit.isco.kitalumniapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import edu.kit.isco.kitalumniapp.settings.ImpressumActivity;

/**
 * Created by Kristina on 20.3.2015 г..
 */
public class ImpressumActivityTest extends ActivityInstrumentationTestCase2<ImpressumActivity> {
    private ImpressumActivity impressumActivity;
    private TextView titleImpressumTextTest;

    public ImpressumActivityTest() {
        super(ImpressumActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        impressumActivity = getActivity();
        titleImpressumTextTest = (TextView) impressumActivity.findViewById(R.id.TitleImpressum);
    }

    /*Test that verify if the test fixture has been set up correctly.*/
    public void testPreconditions() {
        assertNotNull(impressumActivity);
        assertNotNull(titleImpressumTextTest);
    }

    /*Test that check if  the TextView of Impressum Title has the correct label text. */
    public void testImpressumTextView() {
        final String expected = impressumActivity.getString(R.string.app_name);
        final String actual = titleImpressumTextTest.getText().toString();
        assertEquals(expected, actual);
    }
}
