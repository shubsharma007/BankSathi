package com.example.bank_ui.Notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class Channel extends Application {
    // Channel ID for the FCM channel
    static final String FCM_CHANNEL_ID = "channel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    // Method to create the FCM notification channel
    private void createNotificationChannel() {
        // Check if the device is running on Android Oreo (API level 26) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the notification channel with a unique ID, name, and importance level
            NotificationChannel channel = new NotificationChannel(FCM_CHANNEL_ID, "FCM Channel", NotificationManager.IMPORTANCE_DEFAULT);

            // Set any additional properties for the channel
            // For example, you can set the channel description and LED color
            channel.setDescription("FCM Channel Description");
            channel.setLightColor(Color.GREEN);

            // Register the channel with the system NotificationManager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Method to get the FCM channel ID
    public static String getFCMChannelId() {
        return FCM_CHANNEL_ID;
    }
}
