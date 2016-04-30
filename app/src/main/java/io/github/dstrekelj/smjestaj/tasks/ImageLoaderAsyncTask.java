package io.github.dstrekelj.smjestaj.tasks;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import io.github.dstrekelj.smjestaj.utils.BitmapUtils;

/**
 * Created by Domagoj on 30.4.2016..
 */
public class ImageLoaderAsyncTask extends AsyncTask<String, Void, Bitmap> {

    AssetManager assetManager;
    ImageView imageView;

    public ImageLoaderAsyncTask(AssetManager assetManager, ImageView imageView) {
        Log.d("ImageLoader", "constructor");
        this.assetManager = assetManager;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.d("ImageLoader", "doInBackground - " + params[0]);

        Bitmap bitmap = null;

        try {
            InputStream inputStream = assetManager.open(params[0]);
            BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
            bitmapFactoryOptions.inSampleSize = 1;
            bitmap = BitmapFactory.decodeStream(inputStream, null, bitmapFactoryOptions);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d("ImageLoader", "onPostExecute");
        imageView.setImageBitmap(bitmap);
        this.imageView = null;
    }
}
