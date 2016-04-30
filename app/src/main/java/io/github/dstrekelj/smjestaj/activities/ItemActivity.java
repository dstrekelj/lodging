package io.github.dstrekelj.smjestaj.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.LodgingModel;
import io.github.dstrekelj.smjestaj.tasks.ImageLoaderAsyncTask;
import io.github.dstrekelj.smjestaj.utils.AssetsProvider;

public class ItemActivity extends AppCompatActivity {

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

        ivBanner = (ImageView) findViewById(R.id.fragment_item_detail_banner);
        rbRating = (RatingBar) findViewById(R.id.fragment_item_detail_rating);
        tvHeading = (TextView) findViewById(R.id.fragment_item_detail_heading);
        tvSubheading = (TextView) findViewById(R.id.fragment_item_detail_subheading);
        tvBody = (TextView) findViewById(R.id.fragment_item_detail_body);

        ivGalleryItem1 = (ImageView) findViewById(R.id.activity_item_gallery_item1);
        ivGalleryItem2 = (ImageView) findViewById(R.id.activity_item_gallery_item2);
        ivGalleryItem3 = (ImageView) findViewById(R.id.activity_item_gallery_item3);

        Gson gson = new Gson();
        final LodgingModel lodgingModel = gson.fromJson(getIntent().getStringExtra(LodgingModel.ID), LodgingModel.class);

        getSupportActionBar().setTitle(lodgingModel.getName());

        new ImageLoaderAsyncTask(getAssets(), ivBanner).execute(lodgingModel.getBanner());
        new ImageLoaderAsyncTask(getAssets(), ivGalleryItem1).execute(lodgingModel.getImages().get(1));
        new ImageLoaderAsyncTask(getAssets(), ivGalleryItem2).execute(lodgingModel.getImages().get(2));
        new ImageLoaderAsyncTask(getAssets(), ivGalleryItem3).execute(lodgingModel.getImages().get(3));

        rbRating.setRating(lodgingModel.getRating());
        tvHeading.setText(lodgingModel.getName());
        tvSubheading.setText(lodgingModel.getFullAddress());
        tvBody.setText(lodgingModel.getDescription());

        ivGalleryItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
