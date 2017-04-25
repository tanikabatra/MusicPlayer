package com.training.musicplayer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOD on 3/28/2017.
 */

public class FileAdapter extends ArrayAdapter<FileBean> {
    Context context;
    int resource;
    ArrayList<FileBean> fileList;


    public FileAdapter(Context context, int resource, ArrayList<FileBean> fileList) {
        super(context, resource, fileList);
        this.context=context;
        this.resource=resource;
        this.fileList=fileList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        TextView txtTitle = (TextView)view.findViewById(R.id.textView);

        FileBean fb = fileList.get(position);

        imageView.setBackgroundResource(fb.getIcon());
        txtTitle.setText(fb.getTitle());


        return view;
    }
}
