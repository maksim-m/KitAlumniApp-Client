package edu.kit.isco.kitalumniapp;



/**
 * Created by Kristina on 20.3.2015 г..
 */
public class MainActivityTest extends android.test.ActivityInstrumentationTestCase2<MainActivity> {
   private MainActivity mainActivity;



    public MainActivityTest() {
        super(MainActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
    }


}
