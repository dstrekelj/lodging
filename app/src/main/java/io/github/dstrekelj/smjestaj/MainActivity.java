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

public class MainActivity extends AppCompatActivity implements LodgingsJsonReaderAsyncTask.ILodgingsJsonReader {

    ListView lvItems;

    LodgingAdapter lodgingAdapter;

    LodgingsJsonReaderAsyncTask lodgingsJsonReaderAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.activity_main_items);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LodgingModel lodgingModel = (LodgingModel) lodgingAdapter.getItem(position);
                Toast.makeText(MainActivity.this, "Selected: " + lodgingModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        lodgingAdapter = new LodgingAdapter(new ArrayList<LodgingModel>());
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
        lodgingAdapter.addAll(lodgingModelArrayList);
    }
}
