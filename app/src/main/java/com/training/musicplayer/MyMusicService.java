package com.training.musicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;

public class MyMusicService extends Service {

    MediaPlayer mediaPlayer;

    NotificationManager notificationManager;
    Notification notification;
    NotificationCompat.Builder builder;

    String songToPlay;


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"My Music Service Created ",Toast.LENGTH_LONG).show();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"My Music Service Started ",Toast.LENGTH_LONG).show();

        songToPlay = intent.getStringExtra("keySong");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + songToPlay;



        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.music);
        builder.setContentTitle("Song is playing");
        builder.setContentText("Song: " + songToPlay);

       // builder.setDefaults(Notification.DEFAULT_ALL);

        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.addAction(android.R.drawable.ic_menu_add,"Add",null);
        builder.addAction(android.R.drawable.ic_menu_delete,"Delete",null);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.digital_phone);
        builder.setSound(uri);

        notification =builder.build();

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(101,notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"My Music Service Destroyed ",Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
        mediaPlayer.release();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
