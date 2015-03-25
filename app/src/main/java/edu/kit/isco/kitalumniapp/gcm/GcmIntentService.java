package edu.kit.isco.kitalumniapp.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.fragments.JobsDetailsViewActivity;
import edu.kit.isco.kitalumniapp.settings.SettingsActivity;

/**
 * Service to analyze gcm message and post notification.
 *
 * Created by Stelian Stoev on 10.1.2015 г..
 */
public class GcmIntentService extends IntentService {

    public static final String VIBRATE_CHECKBOX = "vibrate";
    private static final String TAG = "GcmIntentService";
    private static int NOTIFICATION_ID = 1;
    private static String jobUrl;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public GcmIntentService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
            switch (messageType) {
                case GoogleCloudMessaging.
                        MESSAGE_TYPE_SEND_ERROR:
                    Toast.makeText(getBaseContext(), messageType, Toast.LENGTH_LONG).show();
                    break;
                case GoogleCloudMessaging.
                        MESSAGE_TYPE_DELETED:
                    Toast.makeText(getBaseContext(), messageType, Toast.LENGTH_LONG).show();

                    // If it's a regular GCM message, do some work.
                    break;
                case GoogleCloudMessaging.
                        MESSAGE_TYPE_MESSAGE:
                    jobUrl = extras.getString("url");
                    generateNotification(getApplicationContext(), extras.getString("title"));
                    break;
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    /**
     * Gets the payload from the GCM message and displays it as notification on the device
     *
     * @param context context
     * @param message payload
     */
    private static void generateNotification(Context context, String message) {
        long when = System.currentTimeMillis();
        String title = context.getString(R.string.app_name);
        //Vibration Pattern
        long[] pattern = {0, 300, 450, 550};
        final SharedPreferences sharedPreferences = context.getSharedPreferences(SettingsActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
        //Notification with vibration
        NotificationCompat.Builder mBuilder = null;
        if (sharedPreferences.getBoolean(VIBRATE_CHECKBOX, false)) {
            try {
                mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher2)
                        .setContentTitle(title)
                        .setTicker(message)
                        .setWhen(when)
                        .setShowWhen(true)
                        .setAutoCancel(true)
                        .setVibrate(pattern)
                        .setContentText(message);
            } catch (SecurityException se) {
                Log.e(TAG, "Can not initialize NotificationCompat.Builder due security exception: " + se.getLocalizedMessage());
            } catch (Exception e) {
                Log.e(TAG, "Can not initialize NotificationCompat.Builder due exception: " + e.getLocalizedMessage());
            }
        } else { // The same Notification without vibration
            try {
                mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher2)
                        .setContentTitle(title)
                        .setTicker(message)
                        .setWhen(when)
                        .setShowWhen(true)
                        .setAutoCancel(true)
                        .setContentText(message);
            } catch (Exception e) {
                Log.e(TAG, "Can not initialize NotificationCompat.Builder due exception: " + e.getLocalizedMessage());
            }
        }
        if (mBuilder == null) {
            Log.e(TAG, "NotificationCompat.Builder is not initialized!");
            return;
        }
        //Intent who caries all extra information
        Intent resultIntent = new Intent(context, JobsDetailsViewActivity.class);
        resultIntent.putExtra("jobURL", jobUrl);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        // id to identify each and every notification. Has to be unique in order to show every notification and not just the last one.
        NOTIFICATION_ID++;
    }
}
