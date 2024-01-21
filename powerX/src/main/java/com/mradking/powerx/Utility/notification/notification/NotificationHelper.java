package com.mradking.powerx.Utility.notification.notification;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.mradking.powerx.R;


public class NotificationHelper {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void displayNotification(Context context, String title, String text) {
        String CHANNEL_ID = "CUSTOM_NOTIFICATION";
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Custom Notification",
                NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Notification.Builder notification = new Notification.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Change this to your custom icon
                .setAutoCancel(true);

        notificationManager.notify(2, notification.build());
    }


}
