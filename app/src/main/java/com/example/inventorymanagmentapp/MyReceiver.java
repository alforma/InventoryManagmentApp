package com.example.inventorymanagmentapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyReceiver extends BroadcastReceiver {
    static int notifyID;
    String channel_id="test123";
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
        createNotificationChannel(context,channel_id);

        Notification n= new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My business notification ")
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("Test of Notification with an id of :"+Integer.toString(notifyID)).build();

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notifyID++,n);






    }


    private void createNotificationChannel(Context context, String CHANNEL_ID) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}



