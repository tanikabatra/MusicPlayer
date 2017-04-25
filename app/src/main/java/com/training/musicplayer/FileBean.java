package com.training.musicplayer;

/**
 * Created by GOD on 3/28/2017.
 */

public class FileBean {

    int icon;
    String title;

    public FileBean() {
    }

    public FileBean(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
