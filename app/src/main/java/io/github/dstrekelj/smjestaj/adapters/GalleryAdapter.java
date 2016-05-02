package io.github.dstrekelj.smjestaj.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.GalleryItemModel;
import io.github.dstrekelj.smjestaj.tasks.ImageLoaderAsyncTask;

/**
 * Created by Domagoj on 2.5.2016..
 */
public class GalleryAdapter extends BaseAdapter {
    public static final String TAG = GalleryAdapter.class.getSimpleName();

    ArrayList<GalleryItemModel> galleryItemModelArrayList;

    public GalleryAdapter(ArrayList<GalleryItemModel> galleryItemModelArrayList) {
        this.galleryItemModelArrayList = galleryItemModelArrayList;
    }

    @Override
    public int getCount() {
        return galleryItemModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return galleryItemModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GalleryItemViewHolder galleryItemViewHolder = null;
        Context context = parent.getContext();

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fragment_gallery_item, null);

            galleryItemViewHolder = new GalleryItemViewHolder();

            galleryItemViewHolder.ivImage = (ImageView) convertView.findViewById(R.id.fragment_gallery_item_image);

            convertView.setTag(galleryItemViewHolder);
        } else {
            galleryItemViewHolder = (GalleryItemViewHolder) convertView.getTag();
        }

        GalleryItemModel galleryItemModel = this.galleryItemModelArrayList.get(position);

        galleryItemViewHolder.ivImage.setTag(galleryItemModel.getPath());
        new ImageLoaderAsyncTask(context.getAssets(), galleryItemViewHolder.ivImage).execute(galleryItemModel.getPath());

        return convertView;
    }

    static class GalleryItemViewHolder {
        private ImageView ivImage;
    }
}
