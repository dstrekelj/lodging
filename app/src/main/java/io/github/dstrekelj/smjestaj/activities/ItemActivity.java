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

public class ItemActivity extends AppCompatActivity {
    public static final String TAG = ItemActivity.class.getSimpleName();

    ImageView ivBanner;
    RatingBar rbRating;
    TextView tvHeading;
    TextView tvSubheading;
    TextView tvBody;

    ImageView ivGalleryItem1;
    ImageView ivGalleryItem2;
    ImageView ivGalleryItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Log.d(TAG, Environment.getDataDirectory().toString());

        ivBanner = (ImageView) findViewById(R.id.acivity_item_banner);
        rbRating = (RatingBar) findViewById(R.id.activity_item_rating);
        tvHeading = (TextView) findViewById(R.id.activity_item_heading);
        tvSubheading = (TextView) findViewById(R.id.activity_item_subheading);
        tvBody = (TextView) findViewById(R.id.activity_item_body);

        ivGalleryItem1 = (ImageView) findViewById(R.id.activity_item_gallery_item1);
        ivGalleryItem2 = (ImageView) findViewById(R.id.activity_item_gallery_item2);
        ivGalleryItem3 = (ImageView) findViewById(R.id.activity_item_gallery_item3);

        Gson gson = new Gson();
        LodgingModel lodgingModel = gson.fromJson(getIntent().getStringExtra(LodgingModel.TAG), LodgingModel.class);

        getSupportActionBar().setTitle(lodgingModel.getName());

        if (lodgingModel.getBanner() != null) {
            new ImageLoaderAsyncTask(getAssets(), ivBanner).execute(lodgingModel.getBanner());
        }

        if (lodgingModel.getGallery() != null) {
            new ImageLoaderAsyncTask(getAssets(), ivGalleryItem1).execute(lodgingModel.getGallery().get(0));
            new ImageLoaderAsyncTask(getAssets(), ivGalleryItem2).execute(lodgingModel.getGallery().get(1));
            new ImageLoaderAsyncTask(getAssets(), ivGalleryItem3).execute(lodgingModel.getGallery().get(2));
            try {
                StorageUtil.create(this, lodgingModel.getImages().get(0), "img0.jpg");
                StorageUtil.create(this, lodgingModel.getImages().get(1), "img1.jpg");
                StorageUtil.create(this, lodgingModel.getImages().get(2), "img2.jpg");
                StorageUtil.create(this, lodgingModel.getImages().get(3), "img3.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString());

        rbRating.setRating(lodgingModel.getRating());
        tvHeading.setText(lodgingModel.getName());
        tvSubheading.setText(lodgingModel.getFullAddress());
        tvBody.setText(lodgingModel.getDescription());

        File folder = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
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
