package edu.kit.isco.kitalumniapp.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import edu.kit.isco.kitalumniapp.MainActivity;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.settings.SettingsActivity;

/**
 * Created by Stelian Stoev on 10.1.2015 г..
 */
public class GcmIntentService  extends IntentService {

    public static final int NOTIFICATION_ID = 1;

    public static final String VIBRATE_CHECKBOX = "vibrate";

    private static NotificationCompat.Builder mBuilder;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
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
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Toast.makeText(getBaseContext(), messageType, Toast.LENGTH_LONG).show();
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                Toast.makeText(getBaseContext(), messageType, Toast.LENGTH_LONG).show();

                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
               generateNotification(getApplicationContext(), extras.toString());
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    /**
     * Gets the payload from the GCM message and displays it as notification on the device
     * @param context
     * @param message
     */
    private static void generateNotification(Context context, String message) {
        long when = System.currentTimeMillis();
        String title = context.getString(R.string.app_name);
        long[] pattern = {0,300,450,550};
        final SharedPreferences sharedPreferences = context.getSharedPreferences(SettingsActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean(VIBRATE_CHECKBOX, false)) {
                    mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(title)
                    .setTicker(message)
                    .setWhen(when)
                    .setShowWhen(true)
                    .setAutoCancel(true)
                    .setVibrate(pattern)
                    .setContentText(message);
        } else {
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(title)
                    .setTicker(message)
                    .setWhen(when)
                    .setShowWhen(true)
                    .setAutoCancel(true)
                    .setContentText(message);
        }
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
