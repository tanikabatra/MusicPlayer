package com.training.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import java.io.IOException;

public class MyBindMusicService extends Service {

    IBinder ibRef = new MyBinder();
    String songToPlay;
    MediaPlayer mediaPlayer;
    String path;


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"My Music Service Created ",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"My Music Service started ",Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"My Music Service bound ",Toast.LENGTH_LONG).show();
        songToPlay = intent.getStringExtra("keySong");
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + songToPlay;
        mediaPlayer = new MediaPlayer();

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



        return ibRef;
    }

    public void playMusic(){


        mediaPlayer.start();
    }

    public void pauseMusic(){
        mediaPlayer.pause();

    }
    public void stopMusic(){
        mediaPlayer.stop();
       // mediaPlayer.release();

    }

    public void relMed(){
        mediaPlayer.release();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"My Music Service unbound ",Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"My Music Service Destroy ",Toast.LENGTH_LONG).show();

    }


    class MyBinder extends Binder{
        MyBindMusicService getServiceReference(){
            return MyBindMusicService.this;
        }
    }
}
