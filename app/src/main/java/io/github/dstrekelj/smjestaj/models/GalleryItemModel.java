package io.github.dstrekelj.smjestaj.models;

import android.graphics.Bitmap;

/**
 * Created by Domagoj on 2.5.2016..
 */
public class GalleryItemModel {
    public static final String TAG = GalleryItemModel.class.getSimpleName();

    private String path;

    public GalleryItemModel() {}

    public GalleryItemModel(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
