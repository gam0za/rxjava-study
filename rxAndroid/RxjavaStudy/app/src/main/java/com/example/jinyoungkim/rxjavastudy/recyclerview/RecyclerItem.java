package com.example.jinyoungkim.rxjavastudy.recyclerview;

import android.graphics.drawable.Drawable;


public class RecyclerItem {
    Drawable image;
    String title;

    public Drawable getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
