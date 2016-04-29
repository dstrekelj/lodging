package io.github.dstrekelj.smjestaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import io.github.dstrekelj.smjestaj.activities.ItemActivity;
import io.github.dstrekelj.smjestaj.adapters.LodgingAdapter;
import io.github.dstrekelj.smjestaj.models.LodgingModel;
import io.github.dstrekelj.smjestaj.tasks.LodgingsJsonReaderAsyncTask;

public class MainActivity extends AppCompatActivity implements LodgingsJsonReaderAsyncTask.ILodgingsJsonReader {

    ListView lvItems;

    ArrayList<LodgingModel> lodgingModelArrayList;
    LodgingAdapter lodgingAdapter;

    LodgingsJsonReaderAsyncTask lodgingsJsonReaderAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.activity_main_items);

        lodgingModelArrayList = new ArrayList<LodgingModel>();
        lodgingAdapter = new LodgingAdapter(lodgingModelArrayList);
        lvItems.setAdapter(lodgingAdapter);

        lodgingsJsonReaderAsyncTask = new LodgingsJsonReaderAsyncTask(this);
        lodgingsJsonReaderAsyncTask.execute("lodgings.json");

        /*
        Intent i = new Intent(this, ItemActivity.class);
        startActivity(i);
        */
    }

    @Override
    public void onLodgingsJsonReaderPostExecute(ArrayList<LodgingModel> lodgingModelArrayList) {
        Log.i("MainActivity", "onLodgingsJsonReaderPostExecute");
        lodgingAdapter.add(lodgingModelArrayList.get(0));
        lodgingAdapter.add(lodgingModelArrayList.get(1));
    }
}
