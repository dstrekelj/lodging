package io.github.dstrekelj.smjestaj.tasks;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.github.dstrekelj.smjestaj.models.LodgingModel;

/**
 * Performs asynchronous reading of JSON file that contains an array of `LodgingModel` data.
 */
public class LodgingsJsonReaderAsyncTask extends AsyncTask<String, Void, ArrayList<LodgingModel>> {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = LodgingsJsonReaderAsyncTask.class.getSimpleName();

    /**
     * Reference to context's asset manager.
     */
    AssetManager assetManager;

    /**
     * Reference to context (activity).
     */
    ILodgingsJsonReader activity;

    /**
     * Constructor. Sets up reference to the current context and asset manager.
     *
     * @param activity  Context of execution
     */
    public LodgingsJsonReaderAsyncTask(ILodgingsJsonReader activity) {
        Log.d(TAG, "constructor");

        this.activity = activity;

        AppCompatActivity context = (AppCompatActivity) activity;
        this.assetManager = context.getAssets();
    }

    /**
     * Opens asset as `InputStream` and uses `JsonReader` and `Gson` to parse it into an array of
     * `LodgingModel` objects.
     *
     * @param params    `String` asset path
     * @return          `ArrayList<LodgingModel>`
     */
    @Override
    protected ArrayList<LodgingModel> doInBackground(String... params) {
        ArrayList<LodgingModel> lodgingModelArrayList = new ArrayList<LodgingModel>();

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        JsonReader jsonReader = null;

        try {
            inputStream = this.assetManager.open(params[0]);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            jsonReader = new JsonReader(inputStreamReader);

            Gson gson = new Gson();

            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                LodgingModel lodgingModel = gson.fromJson(jsonReader, LodgingModel.class);
                lodgingModelArrayList.add(lodgingModel);
            }

            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (jsonReader != null) {
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Release reference to asset manager
        this.assetManager = null;

        return lodgingModelArrayList;
    }

    /**
     * Executes activity callback intended for this task, passing along the created array of
     * lodgings.
     *
     * @param lodgingModelArrayList Array of `LodgingModel` objects parsed from JSON asset
     */
    @Override
    protected void onPostExecute(ArrayList<LodgingModel> lodgingModelArrayList) {
        activity.onLodgingsJsonReaderPostExecute(lodgingModelArrayList);
        // Release reference to activity
        this.activity = null;
    }

    /**
     * Activities should implement this interface when using `LodgingsJsonReaderAsyncTask`.
     */
    public interface ILodgingsJsonReader {
        public void onLodgingsJsonReaderPostExecute(ArrayList<LodgingModel> lodgingModelArrayList);
    }
}
