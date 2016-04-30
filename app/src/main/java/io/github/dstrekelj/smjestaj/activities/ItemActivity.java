package io.github.dstrekelj.smjestaj.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import io.github.dstrekelj.smjestaj.R;
import io.github.dstrekelj.smjestaj.models.LodgingModel;

public class ItemActivity extends AppCompatActivity {

    ImageView ivBanner;
    RatingBar rbRating;
    TextView tvHeading;
    TextView tvSubheading;
    TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ivBanner = (ImageView) findViewById(R.id.fragment_item_detail_banner);
        rbRating = (RatingBar) findViewById(R.id.fragment_item_detail_rating);
        tvHeading = (TextView) findViewById(R.id.fragment_item_detail_heading);
        tvSubheading = (TextView) findViewById(R.id.fragment_item_detail_subheading);
        tvBody = (TextView) findViewById(R.id.fragment_item_detail_body);

        Gson gson = new Gson();
        LodgingModel lodgingModel = gson.fromJson(getIntent().getStringExtra(LodgingModel.ID), LodgingModel.class);

        rbRating.setRating(lodgingModel.getRating());
        tvHeading.setText(lodgingModel.getName());
        tvSubheading.setText(lodgingModel.getFullAddress());
        tvBody.setText(lodgingModel.getDescription());

        getSupportActionBar().setTitle(lodgingModel.getName());
    }
}
