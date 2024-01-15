package com.example.service2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private String  channelId = "MyForegroundServiceChannel";
    MediaPlayer mp;
//    private static final String CHANNEL_ID = "your_channel_id";

    public MyService() {
    }

    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.music);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1,createNotificationChannel());
        if (!mp.isLooping()) {
            mp.start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void stopMusic() {
        if (mp != null && mp.isPlaying()) {
            mp.stop();
//            mp.release();
//            mp = null;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
    private Notification createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "My Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Intent  intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent  = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("My Foreground Service")
                .setContentText("Service is running")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.BigPictureStyle());
        return notificationBuilder.build();
    }

//    private Notification createNotification() {
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
//                .setContentTitle("My Foreground Service")
//                .setContentText("Service is running")
//                .setSmallIcon(R.drawable.ic_launcher_background);
//                return notificationBuilder.build();
//    }

}

//        Intent notification = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                0,notification, PendingIntent.FLAG_IMMUTABLE);
//        Notification notification1= new NotificationCompat.Builder(
//                this,CHANNEL_ID)
//                .setContentTitle("Notification")
//                .setContentText("Music")
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentIntent(pendingIntent)
//                .build();
//        startForeground(1,notification1);





