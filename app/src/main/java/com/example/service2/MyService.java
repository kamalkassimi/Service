package com.example.service2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer mp ;
    public MyService() {
    }
    public  void onCreate(){
        super.onCreate();
       mp =  MediaPlayer.create(this,R.raw.music);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         if (!mp.isLooping()){
             mp.start();
         }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}