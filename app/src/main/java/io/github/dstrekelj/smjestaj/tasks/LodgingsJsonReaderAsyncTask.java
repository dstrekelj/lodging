package io.github.dstrekelj.smjestaj.tasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import io.github.dstrekelj.smjestaj.models.LodgingModel;

/**
 * Created by Domagoj on 29.4.2016..
 */
public class LodgingsJsonReaderAsyncTask extends AsyncTask<String, Void, ArrayList<LodgingModel>>{

    ILodgingsJsonReader activity;

    public LodgingsJsonReaderAsyncTask(ILodgingsJsonReader activity) {
        Log.i("LodgingsJsonReader", "constructor");
        this.activity = activity;
    }

    @Override
    protected ArrayList<LodgingModel> doInBackground(String... params) {
        Log.i("LodgingsJsonReader", "doInBackground - " + params[0]);

        AppCompatActivity context = (AppCompatActivity) activity;
        ArrayList<LodgingModel> lodgingModelArrayList = new ArrayList<LodgingModel>();

        try {
            InputStream inputStream = context.getResources().getAssets().open(params[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            JsonReader jsonReader = new JsonReader(inputStreamReader);
            Gson gson = new Gson();

            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                LodgingModel lodgingModel = gson.fromJson(jsonReader, LodgingModel.class);
                lodgingModelArrayList.add(lodgingModel);
            }

            jsonReader.endArray();
            jsonReader.close();
            inputStream.close();

            inputStream = null;
            inputStreamReader = null;
            jsonReader = null;
            gson = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        context = null;

        return lodgingModelArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<LodgingModel> lodgingModelArrayList) {
        Log.i("LodgingsJsonReader", "onPostExecute");
        Log.i("LodgingsJsonReader", "lodgings count: " + lodgingModelArrayList.size());
        activity.onLodgingsJsonReaderPostExecute(lodgingModelArrayList);
        this.activity = null;
    }

    public interface ILodgingsJsonReader {
        public void onLodgingsJsonReaderPostExecute(ArrayList<LodgingModel> lodgingModelArrayList);
    }
}
