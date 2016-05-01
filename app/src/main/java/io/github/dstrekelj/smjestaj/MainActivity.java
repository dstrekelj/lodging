package io.github.dstrekelj.smjestaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import io.github.dstrekelj.smjestaj.activities.ItemActivity;
import io.github.dstrekelj.smjestaj.adapters.LodgingAdapter;
import io.github.dstrekelj.smjestaj.models.LodgingModel;
import io.github.dstrekelj.smjestaj.tasks.LodgingsJsonReaderAsyncTask;

/**
 * Application entry point. Lists items (lodgings) from `assets/lodgings.json` in a `ListView`.
 */
public class MainActivity extends AppCompatActivity implements LodgingsJsonReaderAsyncTask.ILodgingsJsonReader, AdapterView.OnItemClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    ListView lvItems;
    LodgingAdapter lodgingAdapter;
    LodgingsJsonReaderAsyncTask lodgingsJsonReaderAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.activity_main_items);
        lvItems.setOnItemClickListener(this);

        lodgingAdapter = new LodgingAdapter(new ArrayList<LodgingModel>());
        lvItems.setAdapter(lodgingAdapter);

        lodgingsJsonReaderAsyncTask = new LodgingsJsonReaderAsyncTask(this);
        lodgingsJsonReaderAsyncTask.execute("lodgings.json");
    }

    @Override
    public void onLodgingsJsonReaderPostExecute(ArrayList<LodgingModel> lodgingModelArrayList) {
        Log.d(TAG, "onLodgingsJsonReaderPostExecute");

        lodgingAdapter.addAll(lodgingModelArrayList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick");

        LodgingModel lodgingModel = (LodgingModel) parent.getItemAtPosition(position);
        String json = new Gson().toJson(lodgingModel);

        Log.d(TAG, "Sending: " + json);

        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(LodgingModel.TAG, json);
        startActivity(intent);
    }
}
