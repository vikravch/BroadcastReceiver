package com.example.i.broadcastreceiver;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LogReceive","Receive");
        switch (intent.getAction()){
            case "android.intent.action.AIRPLANE_MODE":
                showNotification(context,"Airplane mode changed",1);
                break;
            case "android.intent.action.BOOT_COMPLETED":
                showNotification(context,"Boot complete",2);
                break;
            case "android.provider.Telephony.SMS_RECEIVED":
                showNotification(context,"New SMS",3);
                break;
            case "android.net.wifi.WIFI_STATE_CHANGED":
                showNotification(context,"WiFi changed",1);
                break;
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(Context context, String s, int id) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("request",0);
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.putExtra("request",1);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,intent1,0);
        PendingIntent pendingIntentSecond = PendingIntent.getActivity(context,2,intent,0);
        PendingIntent pendingIntentThird = PendingIntent.getActivity(context,3,intent,0);



        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(context)
                .setContentTitle("MyReceiver")
                .setContentText(s)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.mipmap.ic_launcher, "Show", pendingIntentSecond)
                .addAction(R.mipmap.ic_launcher, "Hide", pendingIntentThird)
                .addAction(R.mipmap.ic_launcher,"Smile",pendingIntent)
                .build();
        notificationManager.notify(id, notification);

    }
}
