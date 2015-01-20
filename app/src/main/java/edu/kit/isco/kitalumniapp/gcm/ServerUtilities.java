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

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import edu.kit.isco.kitalumniapp.R;

/**
 * Created by Stelian Stoev on 14.1.2015 г..
 */
public class ServerUtilities {

        static final String SERVER_URL = "http://s18028446.onlinehome-server.info:8080/gcm-demo";
        /**
         * Tag used on log messages.
         */
        static final String TAG = "GCMDemo";

        private static final int MAX_ATTEMPTS = 5;
        private static final int BACKOFF_MILLI_SECONDS = 2000;
        private static final Random random = new Random();

        /**
         * Register this account/device pair within the server.
         *
         */
        public void register(final Context context, final String regId) {
            Log.i(TAG, "registering device (regId = " + regId + ")");
            String serverUrl = SERVER_URL + "/register";
            Map<String, String> params = new HashMap<String, String>();
            params.put("regId", regId);
            long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
            // Once GCM returns a registration id, we need to register it in the
            // demo server. As the server might be down, we will retry it a couple
            // times.
            for (int i = 1; i <= MAX_ATTEMPTS; i++) {
                Log.d(TAG, "Attempt #" + i + " to register");
                try {
                    displayMessage(context, context.getString(
                            R.string.server_registering, i, MAX_ATTEMPTS));
                    post(serverUrl, params);
                    //GCMRegistrar.setRegisteredOnServer(context, true);
                    String message = context.getString(R.string.server_registered);
                    displayMessage(context, message);
                    return;
                } catch (IOException e) {
                    // Here we are simplifying and retrying on any error; in a real
                    // application, it should retry only on unrecoverable errors
                    // (like HTTP error code 503).
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
            String serverUrl = SERVER_URL + "/unregister";
            Map<String, String> params = new HashMap<String, String>();
            params.put("regId", regId);
            try {
                post(serverUrl, params);
                //GCMRegistrar.setRegisteredOnServer(context, false);
                String message = context.getString(R.string.server_unregistered);
                displayMessage(context, message);
            } catch (IOException e) {
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
         * Issue a POST request to the server.
         *
         * @param endpoint POST address.
         * @param params request parameters.
         *
         * @throws IOException propagated from POST.
         */
        private void post(String endpoint, Map<String, String> params)
                throws IOException {
            URL url;
            try {
                url = new URL(endpoint);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("invalid url: " + endpoint);
            }
            StringBuilder bodyBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            // constructs the POST body using the parameters
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                bodyBuilder.append(param.getKey()).append('=')
                        .append(param.getValue());
                if (iterator.hasNext()) {
                    bodyBuilder.append('&');
                }
            }
            String body = bodyBuilder.toString();
            Log.v(TAG, "Posting '" + body + "' to " + url);
            byte[] bytes = body.getBytes();
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                // post the request
                OutputStream out = conn.getOutputStream();
                out.write(bytes);
                out.close();
                // handle the response
                int status = conn.getResponseCode();
                if (status != 200) {
                    throw new IOException("Post failed with error code " + status);
                }
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }

    /**
     * Intent used to display a message in the screen.
     */
    static final String DISPLAY_MESSAGE_ACTION =
            "com.google.android.gcm.demo.app.DISPLAY_MESSAGE";

    /**
     * Intent's extra that contains the message to be displayed.
     */
    static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
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

}
