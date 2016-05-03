package io.github.dstrekelj.smjestaj.activities;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.LodgingModel;
import io.github.dstrekelj.smjestaj.tasks.ImageLoaderAsyncTask;
import io.github.dstrekelj.smjestaj.utils.SingleMediaScanner;
import io.github.dstrekelj.smjestaj.utils.StorageUtil;

/**
 * Provides a detailed view of selected lodging item.
 */
public class ItemActivity extends AppCompatActivity {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = ItemActivity.class.getSimpleName();

    /**
     * Banner. Intended for first lodging image.
     */
    ImageView ivBanner;

    /**
     * Rating bar. Intended for lodging rating.
     */
    RatingBar rbRating;

    /**
     * Heading. Intended for lodging name.
     */
    TextView tvHeading;

    /**
     * Subheading. Intended for lodging full address.
     */
    TextView tvSubheading;

    /**
     * Body. Intended for lodging description.
     */
    TextView tvBody;

    /**
     * Gallery item #1. Intended for lodging image #2.
     */
    ImageView ivGalleryItem1;

    /**
     * Gallery item #2. Intended for lodging image #3.
     */
    ImageView ivGalleryItem2;

    /**
     * Gallery item #3. Intended for lodging image #4.
     */
    ImageView ivGalleryItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        this.ivBanner = (ImageView) findViewById(R.id.acivity_item_banner);
        this.rbRating = (RatingBar) findViewById(R.id.activity_item_rating);
        this.tvHeading = (TextView) findViewById(R.id.activity_item_heading);
        this.tvSubheading = (TextView) findViewById(R.id.activity_item_subheading);
        this.tvBody = (TextView) findViewById(R.id.activity_item_body);

        this.ivGalleryItem1 = (ImageView) findViewById(R.id.activity_item_gallery_item1);
        this.ivGalleryItem2 = (ImageView) findViewById(R.id.activity_item_gallery_item2);
        this.ivGalleryItem3 = (ImageView) findViewById(R.id.activity_item_gallery_item3);

        Gson gson = new Gson();
        // Retrieving the item data sent from `MainActivity`
        LodgingModel lodgingModel = gson.fromJson(getIntent().getStringExtra(LodgingModel.TAG), LodgingModel.class);

        // Update activity name in action bar
        getSupportActionBar().setTitle(lodgingModel.getName());

        this.rbRating.setRating(lodgingModel.getRating());
        this.tvHeading.setText(lodgingModel.getName());
        this.tvSubheading.setText(lodgingModel.getFullAddress());
        this.tvBody.setText(lodgingModel.getDescription());

        // TODO: Look into a more scalable gallery solution (remember: `GridView` did not work out)

        if (lodgingModel.getBanner() != null) {
            new ImageLoaderAsyncTask(getAssets(), this.ivBanner).execute(lodgingModel.getBanner());

            // Store image in external file directory so that the media scanner can scan it
            StorageUtil.create(this, lodgingModel.getImages().get(0), "img0.jpg");
        }

        if (lodgingModel.getGallery() != null) {
            new ImageLoaderAsyncTask(getAssets(), this.ivGalleryItem1).execute(lodgingModel.getGallery().get(0));
            new ImageLoaderAsyncTask(getAssets(), this.ivGalleryItem2).execute(lodgingModel.getGallery().get(1));
            new ImageLoaderAsyncTask(getAssets(), this.ivGalleryItem3).execute(lodgingModel.getGallery().get(2));

            // Store images in external file directory so that the media scanner can scan them
            StorageUtil.create(this, lodgingModel.getImages().get(1), "img1.jpg");
            StorageUtil.create(this, lodgingModel.getImages().get(2), "img2.jpg");
            StorageUtil.create(this, lodgingModel.getImages().get(3), "img3.jpg");
        }

        // TODO: Optimise the whole "copy-to-storage, scan, and load" process?

        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // Only allow gallery behaviour if the external files directory is accessible for reading
        if (externalFilesDir != null && externalFilesDir.canRead()) {
            // Get list of files in external files directory and run the media scanner on image click
            File folder = new File(externalFilesDir.getPath());
            final File[] files = folder.listFiles();

            ivBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SingleMediaScanner(ItemActivity.this, files[0]);
                }
            });

            ivGalleryItem1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SingleMediaScanner(ItemActivity.this, files[1]);
                }
            });

            ivGalleryItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SingleMediaScanner(ItemActivity.this, files[2]);
                }
            });

            ivGalleryItem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SingleMediaScanner(ItemActivity.this, files[3]);
                }
            });
        }
    }
}
