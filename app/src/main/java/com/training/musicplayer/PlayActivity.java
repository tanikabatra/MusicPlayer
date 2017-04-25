package com.training.musicplayer;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet4Address;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtName;
    Button btnPlay,btnStop,btnPause;
    String songToPlay;
    MyBindMusicService myBindService;







    void initViews(){
        txtName = (TextView)findViewById(R.id.textViewName);
        btnPlay = (Button)findViewById(R.id.buttonPlay);
        btnStop = (Button)findViewById(R.id.buttonStop);
        btnPause = (Button)findViewById(R.id.buttonPause);
        Intent rcv = getIntent();
        songToPlay = rcv.getStringExtra("keySong");
        txtName.setText(songToPlay);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();

        Intent intent = new Intent(PlayActivity.this,MyBindMusicService.class);
        intent.putExtra("keySong",songToPlay);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myBindService.stopMusic();
        myBindService.relMed();
        unbindService(connection);

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBindMusicService.MyBinder myBinder = (MyBindMusicService.MyBinder)service;
            myBindService = myBinder.getServiceReference();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };




    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id==R.id.buttonPlay){
            /*Intent intent = new Intent(PlayActivity.this,MyBindMusicService.class);
            intent.putExtra("keySong",songToPlay);
            startService(intent);*/
          myBindService.playMusic();

        }
        else if (id==R.id.buttonPause){
            /*Intent i = new Intent(PlayActivity.this,MyBindMusicService.class);
            stopService(i);*/
            myBindService.pauseMusic();

        }
        else if(id==R.id.buttonStop) {
            myBindService.stopMusic();
            //unbindService(connection);

        }

    }
}
