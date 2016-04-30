package io.github.dstrekelj.smjestaj.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.LodgingModel;

/**
 * Created by Domagoj on 29.4.2016..
 */
public class LodgingAdapter extends BaseAdapter {

    ArrayList<LodgingModel> lodgingModelArrayList;

    public LodgingAdapter(ArrayList<LodgingModel> lodgingModelArrayList) {
        this.lodgingModelArrayList = lodgingModelArrayList;
    }

    @Override
    public int getCount() {
        return lodgingModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return lodgingModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("LodgingAdapter", "getView");
        ItemViewHolder itemViewHolder;
        Context context = parent.getContext();

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_main_item, null);

            itemViewHolder = new ItemViewHolder();

            itemViewHolder.ivItemImage = (ImageView) convertView.findViewById(R.id.activity_main_item_image);
            itemViewHolder.tvItemHeading = (TextView) convertView.findViewById(R.id.activity_main_item_heading);
            itemViewHolder.tvItemBody = (TextView) convertView.findViewById(R.id.activity_main_item_body);

            convertView.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) convertView.getTag();
        }

        LodgingModel lodgingModel = this.lodgingModelArrayList.get(position);

        itemViewHolder.tvItemHeading.setText(lodgingModel.getName());
        itemViewHolder.tvItemBody.setText(lodgingModel.getFullAddress());

        return convertView;
    }

    public void addAll(ArrayList<LodgingModel> lodgingModelArrayList) {
        this.lodgingModelArrayList = lodgingModelArrayList;
        this.notifyDataSetChanged();
    }

    static class ItemViewHolder {
        private ImageView ivItemImage;
        private TextView tvItemHeading;
        private TextView tvItemBody;
    }
}
