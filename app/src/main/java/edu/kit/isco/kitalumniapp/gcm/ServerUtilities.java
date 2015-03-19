package edu.kit.isco.kitalumniapp.gcm;


/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessTag;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessUser;

/**
 * Binds in one place methods for register, unregister and updating for notifications.
 * Created by Stelian Stoev on 14.1.2015 г..
 */
public class ServerUtilities {

    static final String SERVER_URL = "yourURL";
    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCMDemo";
    /**
     * Intent used to display a message in the screen.
     */
    static final String DISPLAY_MESSAGE_ACTION =
            "com.google.android.gcm.demo.app.DISPLAY_MESSAGE";
    /**
     * Intent's extra that contains the message to be displayed.
     */
    static final String EXTRA_MESSAGE = "message";
    private static final int MAX_ATTEMPTS = 5;
    private static final int BACKOFF_MILLI_SECONDS = 2000;
    private static final Random random = new Random();
    private static BigInteger bigInteger = null;
    private List<DataAccessTag> fullTagsList;

    /**
     * Notifies UI to display a message.
     * <p/>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }

    /**
     * Checks if the list has been created and if it hasn`t, then it puts all Tags in one single list.
     * @return fully populated List of DataAccessTags
     */

    private List<DataAccessTag> populateTagList() {
        if (fullTagsList == null) {
            fullTagsList = new ArrayList<>();
            fullTagsList.add(DataAccessTag.DATA_ADMINISTRATION);
            fullTagsList.add(DataAccessTag.TRAINEE);
            fullTagsList.add(DataAccessTag.CLERK);
            fullTagsList.add(DataAccessTag.GRADUAND);
            fullTagsList.add(DataAccessTag.DOCTORAND);
            fullTagsList.add(DataAccessTag.ENGINEER);
            fullTagsList.add(DataAccessTag.INDUSTRIAL);
            fullTagsList.add(DataAccessTag.SALES_OCCUPATION);
            fullTagsList.add(DataAccessTag.THRESHOLD_WORKER);
            fullTagsList.add(DataAccessTag.PROFESSOR);
            fullTagsList.add(DataAccessTag.TECHNICAL_EMPLOYEE);
            fullTagsList.add(DataAccessTag.STUDENT_RESEARCH_PROJECT);
            fullTagsList.add(DataAccessTag.ADMINISTRATION);
            fullTagsList.add(DataAccessTag.SCIENTIST);
            fullTagsList.add(DataAccessTag.OTHERS);
        }

        return fullTagsList;
    }

    /**
     * Generates password the first time and then returns it
     * every other time.
     * @return password
     */
    private static BigInteger getPassword() {
        if (bigInteger == null) {
            SecureRandom password = new SecureRandom();
            bigInteger= new BigInteger(64, password);
        }
        return bigInteger;
    }
    /**
     * Register this account/device pair within the server.
     */
    public void register(final Context context, final String regId) {
        Log.i(TAG, "registering device (regId = " + regId + ")");
        DataAccessUser user = new DataAccessUser(regId, populateTagList(), getPassword().toString(32));
        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
        // Once GCM returns a registration id, we need to register it in the
        // app server. The registration will be repeated maximal 5 times.
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            Log.d(TAG, "Attempt #" + i + " to register");
            try {
                displayMessage(context, context.getString(
                        R.string.server_registering, i, MAX_ATTEMPTS));
                Ion.with(context)
                        .load("POST", SERVER_URL)
                        .setJsonPojoBody(user)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.i(TAG, "Registration delivered to APP Server");
                            }
                        });
                String message = context.getString(R.string.server_registered);
                displayMessage(context, message);
                return;
            } catch (Exception e) {
                Log.e(TAG, "Failed to register on attempt " + i + ":" + e);
                if (i == MAX_ATTEMPTS) {
                    break;
                }
                try {
                    Log.d(TAG, "Sleeping for " + backoff + " ms before retry");
                    Thread.sleep(backoff);
                } catch (InterruptedException e1) {
                    // Activity finished before we complete - exit.
                    Log.d(TAG, "Thread interrupted: abort remaining retries!");
                    Thread.currentThread().interrupt();
                    return;
                }
                // increase backoff exponentially
                backoff *= 2;
            }
        }
        String message = context.getString(R.string.server_register_error,
                MAX_ATTEMPTS);
        displayMessage(context, message);
    }

    /**
     * Unregister this account/device pair within the server.
     */
    public void unregister(final Context context, final String regId) {
        Log.i(TAG, "unregistering device (regId = " + regId + ")");
        DataAccessUser user = new DataAccessUser(regId, null, getPassword().toString(32));
        try {
            Ion.with(context)
                    .load("DELETE", SERVER_URL)
                    .setJsonPojoBody(user)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            Log.i(TAG, "Unregistrating Success");
                        }
                    });
            String message = context.getString(R.string.server_unregistered);
            displayMessage(context, message);
        } catch (Exception e) {
            // At this point the device is unregistered from GCM, but still
            // registered in the server.
            // We could try to unregister again, but it is not necessary:
            // if the server tries to send a message to the device, it will get
            // a "NotRegistered" error message and should unregister the device.
            String message = context.getString(R.string.server_unregister_error,
                    e.getMessage());
            displayMessage(context, message);
        }
    }

    /**
     * Updating user
     */

    public void updateUser(Context context, List<DataAccessTag> tags, String regId) {
        Log.i(TAG, "registering device (regId = " + regId + ")");
        fullTagsList = tags;
        DataAccessUser user = new DataAccessUser(regId, tags, getPassword().toString(32));
        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
        // The update will be repeated maximal 5 times.
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            Log.d(TAG, "Attempt #" + i + " to update");
            try {
                displayMessage(context, context.getString(
                        R.string.server_registering, i, MAX_ATTEMPTS));
                Ion.with(context)
                        .load("PUT", SERVER_URL)
                        .setJsonPojoBody(user)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                Log.i(TAG, "Updating info for user on APP Server");
                            }
                        });
                String message = context.getString(R.string.server_registered);
                displayMessage(context, message);
                return;
            } catch (Exception e) {
                Log.e(TAG, "Failed to update on attempt " + i + ":" + e);
                if (i == MAX_ATTEMPTS) {
                    break;
                }
                try {
                    Log.d(TAG, "Sleeping for " + backoff + " ms before retry");
                    Thread.sleep(backoff);
                } catch (InterruptedException e1) {
                    // Activity finished before we complete - exit.
                    Log.d(TAG, "Thread interrupted: abort remaining retries!");
                    Thread.currentThread().interrupt();
                    return;
                }
                // increase backoff exponentially
                backoff *= 2;
            }
        }
        String message = context.getString(R.string.server_register_error,
                MAX_ATTEMPTS);
        displayMessage(context, message);
    }
}
