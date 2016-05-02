package io.github.dstrekelj.smjestaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.GalleryItemModel;
import io.github.dstrekelj.smjestaj.utils.MultipleMediaScanner;
import io.github.dstrekelj.smjestaj.utils.SingleMediaScanner;

/**
 * Created by Domagoj on 2.5.2016..
 */
public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = GalleryFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView gridView = (GridView) view;
        gridView.setOnItemClickListener(this);
        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onClick");
        GalleryItemModel galleryItemModel = (GalleryItemModel) parent.getAdapter().getItem(position);
        Context context = view.getContext();
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + galleryItemModel.getPath());
        Log.d(TAG, file.getAbsolutePath());
        new SingleMediaScanner(context, file);
    }
}
