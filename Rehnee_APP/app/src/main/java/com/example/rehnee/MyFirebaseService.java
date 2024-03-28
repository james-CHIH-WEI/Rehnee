package com.example.rehnee;


import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData() != null) {

            if (!remoteMessage.getData().get("date").equals("") & !remoteMessage.getData().get("time").equals("")) {
                SQLite_Chat sqLite_chat = new SQLite_Chat(MyFirebaseService.this);
                sqLite_chat.insert(
                        remoteMessage.getData().get("Id"),
                        "1",
                        remoteMessage.getData().get("content"),
                        remoteMessage.getData().get("date"),
                        remoteMessage.getData().get("time")
                );
            }

            if (getRunningActivityName().equals("com.example.rehnee.Chat")) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MyFirebaseService.this, Chat.class);
                intent.putExtra("Id", remoteMessage.getData().get("Id"));
                startActivity(intent);
            }else{
                sendNotification(remoteMessage.getData().get("content"));
            }

        }

    }

    private String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i("MyFirebaseService", "token " + s);
    }

    private void sendNotification(String content) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("status", "from_FCM");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = "通知";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("Rehnee訊息")
                        .setSmallIcon(R.mipmap.rehnee_foreground)
                        .setContentText(content)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "通知",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
}
