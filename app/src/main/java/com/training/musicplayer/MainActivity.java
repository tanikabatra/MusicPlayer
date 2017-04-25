package com.training.musicplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<FileBean> fileList;
    FileAdapter fileAdapter;



    void initViews(){
        listView = (ListView)findViewById(R.id.listView);
        fileList = new ArrayList<>();

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        readFileSystem(Environment.getExternalStorageDirectory());
    }


    void readFileSystem(File dir){
       // String path = Environment.getExternalStorageDirectory().getAbsolutePath() ;
       // File file = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                FileBean fileBean = new FileBean();


                if (files[i].isDirectory()) {
                    readFileSystem(files[i]);
                } else {
                    if (files[i].getName().endsWith(".mp3")){
                        fileBean.setIcon(R.drawable.music);
                        fileBean.setTitle(files[i].getName());
                        fileList.add(fileBean);

                    }
                }
            }
        }

        /*for (File f : files){
            FileBean fileBean = new FileBean();


            if(f.isFile()&& (f.getName().endsWith(".mp3"))){
                fileBean.setIcon(R.drawable.music);
                fileBean.setTitle(f.getName());
                fileList.add(fileBean);
            }*/
           /* else if (f.isDirectory()){
                fileBean.setIcon(R.drawable.folder);
                fileBean.setTitle(f.getName());
            }*/



        fileAdapter = new FileAdapter(this,R.layout.list_item,fileList);
        listView.setAdapter(fileAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FileBean fb = fileList.get(position);

        Toast.makeText(this,"You Selected: " + fb.getTitle(),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this,PlayActivity.class);
        intent.putExtra("keySong",fb.getTitle());
        startActivity(intent);

    }
}
