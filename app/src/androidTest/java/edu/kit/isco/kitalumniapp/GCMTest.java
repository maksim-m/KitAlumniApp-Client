package edu.kit.isco.kitalumniapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import com.google.android.gms.wearable.Asset;

import junit.framework.Assert;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.dbObjects.DataAccessTag;
import edu.kit.isco.kitalumniapp.gcm.ServerUtilities;
import edu.kit.isco.kitalumniapp.settings.SettingsActivity;

/**
 * Created by Stelian Stoev on 19.3.2015 г..
 */
public class GCMTest extends AndroidTestCase {
    ServerUtilities gcmSU;
    SharedPreferences prefs;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        prefs = getContext().getSharedPreferences(SettingsActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
        gcmSU = new ServerUtilities();
    }

    public void testRegisterWithEmptyRegId() {
        try {
            gcmSU.register(getContext(), null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testUpdateWithEmptyRegId() {
        try {
            gcmSU.updateUser(getContext(), null, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testUnregisterWithEmptyRegId() {
        try {
            gcmSU.unregister(getContext(), null);
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testRegisterUser() {
        try {
            gcmSU.register(getContext(), "testRegId");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testUnregisterUser() {
        try {
            gcmSU.unregister(getContext(), "testRegId");
        } catch (Exception e) {
        }
    }

    public void testUpdateUser() {
        try {
            gcmSU.updateUser(getContext(), new ArrayList<DataAccessTag>(), "testRegId");
        } catch (Exception e) {

        }
    }
}
