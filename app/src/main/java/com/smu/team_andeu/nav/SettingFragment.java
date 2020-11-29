package com.smu.team_andeu.nav;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;

import static android.content.Context.NOTIFICATION_SERVICE;


public class SettingFragment extends Fragment {

    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    private Button button_notify;
    private Button button_cancel;

    private NotificationManager mNotifyManager;

    private final NotificationReceiver mReceiver = new NotificationReceiver();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.setting_fragment, container, false);

        createNotificationChannel();
        // Register the broadcast receiver to receive the update action from
        // the notification.
        getActivity().registerReceiver(mReceiver,
                new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        // Add onClick handlers to all the buttons.
        button_notify = v.findViewById(R.id.notify);
        button_notify.setOnClickListener(view -> {
            // Send the notification
            sendNotification();
        });

        Button button_update = (Button) v.findViewById(R.id.update);
        button_update.setOnClickListener(view -> {
            // Update the notification.
            updateNotification();
        });

        button_cancel = (Button) v.findViewById(R.id.cancel);
        button_cancel.setOnClickListener(view -> {
            // Cancel the notification.
            cancelNotification();
        });

        // Reset the button states. Enable only Notify button and disable
        // update and cancel buttons.
        setNotificationButtonState(true, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroyView();

    }

    protected void createNotificationChannel() {

        // Create a notification manager object.
        mNotifyManager =
                (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            getString(R.string.notification_channel_name),
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    (getString(R.string.notification_channel_description));

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    public void sendNotification() {

        // Sets up the pending intent to update the notification.
        // Corresponds to a press of the Update Me! button.
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(getContext(),
                NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        // Build the notification with all of the parameters using helper
        // method.
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        // Add the action button using the pending intent.
        notifyBuilder.addAction(R.drawable.ic_clear_24,
                getString(R.string.update), updatePendingIntent);

        // Deliver the notification.
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        // Enable the update and cancel buttons but disables the "Notify
        // Me!" button.
        setNotificationButtonState(false, true);
    }

    private NotificationCompat.Builder getNotificationBuilder() {

        // Set up the pending intent that is delivered when the notification
        // is clicked.
        Intent notificationIntent = new Intent(getContext(), MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (getContext(), NOTIFICATION_ID, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification with all of the parameters.
        return new NotificationCompat
                .Builder(getContext(), PRIMARY_CHANNEL_ID)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_done_24)
                .setAutoCancel(true).setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
    }

    public void updateNotification() {

        // Load the drawable resource into the a bitmap image.
        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(), R.drawable.ic_add_24);

        // Build the notification with all of the parameters using helper
        // method.
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        // Update the notification style to BigPictureStyle.
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle(getString(R.string.notification_updated)));

        // Deliver the notification.
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        // Disable the update button, leaving only the cancel button enabled.
        setNotificationButtonState(false, true);
    }

    public void cancelNotification() {
        // Cancel the notification.
        mNotifyManager.cancel(NOTIFICATION_ID);

        // Reset the buttons.
        setNotificationButtonState(true, false);
    }

    void setNotificationButtonState(Boolean isNotifyEnabled, Boolean isCancelEnabled) {
        button_notify.setEnabled(isNotifyEnabled);
        button_cancel.setEnabled(isCancelEnabled);
    }

    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {
        }

        /**
         * Receives the incoming broadcasts and responds accordingly.
         *
         * @param context Context of the app when the broadcast is received.
         * @param intent  The broadcast intent containing the action.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // Update the notification.
            updateNotification();
        }
    }
}
