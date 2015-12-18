package com.example.hank.lab3;

import android.graphics.Bitmap;

/**
 * Created by Hank on 14.12.2015.
 */
public class ImageItem {
    private Bitmap image;

    public ImageItem(Bitmap image) {
        super();
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

}