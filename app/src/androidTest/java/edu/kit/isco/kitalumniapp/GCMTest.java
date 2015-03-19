package edu.kit.isco.kitalumniapp;

import android.test.AndroidTestCase;

import com.google.android.gms.wearable.Asset;

import junit.framework.Assert;
import edu.kit.isco.kitalumniapp.gcm.ServerUtilities;

/**
 * Created by Stelian Stoev on 19.3.2015 г..
 */
public class GCMTest extends AndroidTestCase {
    ServerUtilities gcmSU;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        gcmSU = new ServerUtilities();
    }

    public void testRegisterWithEmptyRedId() {
        try {
            gcmSU.register(getContext(), null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }
    public void testUpdateWithEmptyRedId() {
        try {
            gcmSU.updateUser(getContext(), null, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }
    public void testUnregisterWithEmptyRedId() {
        try {
            gcmSU.unregister(getContext(), null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }
}
