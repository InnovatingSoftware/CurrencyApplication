package com.app.andres.baseapplication.managers;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.app.andres.baseapplication.BaseApplication;
import com.app.andres.baseapplication.R;

import static com.app.andres.baseapplication.utils.AndroidVersionUtilKt.hasMarshmallow;

public class NotificationsManagerApp {

    public static String GENERAL_NOTIFICATION_CHANNEL_ID = "10001";
    public static String SERVICE_NOTIFICATION_CHANNEL_ID = "10002";

    public static String GENERAL_NOTIFICATION_CHANNEL_NAME = "General Notifications";
    public static String SERVICE_NOTIFICATION_CHANNEL_NAME = "SK Services";

    public static void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(createGeneralNotificationChannel());
            createNotificationChannel(createServiceNotificationChannel());
        }
    }

    private static NotificationManager getNotificationManager() {
        return (NotificationManager) BaseApplication.get().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void cancelNotificationWithId(int notificationId) {
        getNotificationManager().cancel(notificationId);
    }

    public static void createNotification(int notificationId, Notification notification) {
        getNotificationManager().notify(notificationId, notification);
    }

    public static StatusBarNotification[] getActiveNotifications() {
        StatusBarNotification[] statusBarNotifications;
        if (hasMarshmallow()) {
            statusBarNotifications = getNotificationManager().getActiveNotifications();
        } else {
            statusBarNotifications = new StatusBarNotification[0];
        }
        return statusBarNotifications;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel createGeneralNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(GENERAL_NOTIFICATION_CHANNEL_ID, GENERAL_NOTIFICATION_CHANNEL_NAME, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        return notificationChannel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel createServiceNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(SERVICE_NOTIFICATION_CHANNEL_ID, SERVICE_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setShowBadge(false);
        return notificationChannel;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(NotificationChannel notificationChannel) {
        getNotificationManager().createNotificationChannel(notificationChannel);
    }

    public static Notification getNotificationForService(Context context, String title, String message, PendingIntent pendingIntent) {
        try {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setContentTitle(title)
                    .setTicker(title)
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setChannelId("10002")
                    .setOngoing(true);

            builder.setSmallIcon(R.mipmap.ic_launcher);


            return builder.build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
