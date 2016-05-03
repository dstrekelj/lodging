package io.github.dstrekelj.smjestaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import io.github.dstrekelj.smjestaj.activities.ItemActivity;
import io.github.dstrekelj.smjestaj.adapters.LodgingAdapter;
import io.github.dstrekelj.smjestaj.models.LodgingModel;
import io.github.dstrekelj.smjestaj.tasks.LodgingsJsonReaderAsyncTask;

/**
 * Application entry point. Lists items (lodgings) from `assets/lodgings.json` in a `ListView`.
 */
public class MainActivity extends AppCompatActivity implements LodgingsJsonReaderAsyncTask.ILodgingsJsonReader, AdapterView.OnItemClickListener {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Lodging items list.
     */
    private ListView lvItems;

    /**
     * Lodging item list adapter.
     */
    private LodgingAdapter lodgingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvItems = (ListView) findViewById(R.id.activity_main_items);
        this.lvItems.setOnItemClickListener(this);

        this.lodgingAdapter = new LodgingAdapter(new ArrayList<LodgingModel>());
        this.lvItems.setAdapter(lodgingAdapter);

        new LodgingsJsonReaderAsyncTask(this).execute(getString(R.string.lodgings_data));
    }

    /**
     * Required by `AdapterView.OnItemClickListener` interface. Sends clicked list item's data to
     * the `ItemActivity` as a JSON string.
     *
     * @param parent    Adapter view
     * @param view      View to insert into adapter view
     * @param position  Item position in adapter view
     * @param id        Item ID
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LodgingModel lodgingModel = (LodgingModel) parent.getItemAtPosition(position);
        // Object is serialized before being sent
        String json = new Gson().toJson(lodgingModel);

        Intent intent = new Intent(this, ItemActivity.class);
        // `LodgingModel` class name is used as identifier
        intent.putExtra(LodgingModel.TAG, json);
        startActivity(intent);
    }

    /**
     * Required by `LodgingsJsonReaderAsyncTask.ILodgingsJsonReader` interface. Refreshes adapter
     * with new list of lodging items.
     *
     * @param lodgingModelArrayList List of lodging items
     */
    @Override
    public void onLodgingsJsonReaderPostExecute(ArrayList<LodgingModel> lodgingModelArrayList) {
        this.lodgingAdapter.addAll(lodgingModelArrayList);
    }
}
