package io.github.dstrekelj.smjestaj.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.LodgingModel;
import io.github.dstrekelj.smjestaj.tasks.ImageLoaderAsyncTask;

/**
 * Adapter for `LodgingModel`.
 */
public class LodgingAdapter extends BaseAdapter {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = LodgingAdapter.class.getSimpleName();

    /**
     * Internal list of lodging items.
     */
    ArrayList<LodgingModel> lodgingModelArrayList;

    /**
     * Constructor.
     *
     * @param lodgingModelArrayList List of lodgings
     */
    public LodgingAdapter(ArrayList<LodgingModel> lodgingModelArrayList) {
        this.lodgingModelArrayList = lodgingModelArrayList;
    }

    /**
     * Gets number of elements.
     *
     * @return Element count
     */
    @Override
    public int getCount() {
        return lodgingModelArrayList.size();
    }

    /**
     * Gets item as `LodgingModel` object.
     *
     * @param position  Item position
     * @return          Item object
     */
    @Override
    public Object getItem(int position) {
        return lodgingModelArrayList.get(position);
    }

    /**
     * Gets item ID (currently returns passed `position` argument).
     *
     * @param position  Item position
     * @return          Item ID
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Prepares the view for an item.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder itemViewHolder = null;
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

        // If no banner image asset can be found, set it to null
        if (lodgingModel.getBanner() == null) {
            itemViewHolder.ivItemImage.setImageBitmap(null);
        } else {
            // Banner images are used as thumbnails in the list view. To discern between different
            // list entries we set a tag for the `ImageLoaderAsyncTask` to use.
            itemViewHolder.ivItemImage.setTag(lodgingModel.getBanner());
            new ImageLoaderAsyncTask(context.getAssets(), itemViewHolder.ivItemImage).execute(lodgingModel.getBanner());
        }

        return convertView;
    }

    /**
     * Adds all items from provided list to the list behind the adapter, removing all previously
     * present entries.
     *
     * @param lodgingModelArrayList List of lodgings
     */
    public void addAll(ArrayList<LodgingModel> lodgingModelArrayList) {
        this.lodgingModelArrayList = lodgingModelArrayList;
        this.notifyDataSetChanged();
    }

    /**
     * Represents the views `activity_main_item.xml` layout.
     */
    static class ItemViewHolder {
        private ImageView ivItemImage;
        private TextView tvItemHeading;
        private TextView tvItemBody;
    }
}
