package io.github.dstrekelj.smjestaj.tasks;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Performs asynchronous loading of an image from the assets folder into an `ImageView`.
 */
public class ImageLoaderAsyncTask extends AsyncTask<String, Void, Bitmap> {
    public static final String TAG = ImageLoaderAsyncTask.class.getSimpleName();

    AssetManager assetManager;
    ImageView imageView;
    String tag;

    /**
     * Constructor. If the view provides a tag it will be used to find the correct view when
     * setting the bitmap.
     *
     * @param assetManager  Current context's `AssetManager`
     * @param imageView     Affected `ImageView`
     */
    public ImageLoaderAsyncTask(AssetManager assetManager, ImageView imageView) {
        Log.d(TAG, "constructor");

        this.assetManager = assetManager;
        this.imageView = imageView;

        this.imageView.setVisibility(View.INVISIBLE);

        if (imageView.getTag() != null) {
            this.tag = imageView.getTag().toString();
        }
    }

    /**
     * Opens the image as an `InputStream` and decodes the stream into a `Bitmap`. Throws exception
     * if asset couldn't be opened.
     *
     * @param params    `String` file path
     * @return          `Bitmap` image
     */
    @Override
    protected Bitmap doInBackground(String... params) {
        Log.d(TAG, "doInBackground - " + params[0]);

        Bitmap bitmap = null;
        InputStream inputStream = null;

        try {
            inputStream = this.assetManager.open(params[0]);
            BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
            // TODO: Actually calculate the appropriate input sample size
            bitmapFactoryOptions.inSampleSize = 1;
            bitmap = BitmapFactory.decodeStream(inputStream, null, bitmapFactoryOptions);
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
        }

        // Release reference to asset manager
        this.assetManager = null;

        return bitmap;
    }

    /**
     * Sets image of `ImageView` to the created `Bitmap`.
     *
     * @param bitmap    `Bitmap` image
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        // Set image bitmap only if bitmap exists, and tags match or don't exist. This is done so
        // that this task can be used to load images asynchronously outside of a ListView.
        if (bitmap != null && (this.imageView.getTag() == null || this.imageView.getTag().toString().equals(this.tag))) {
            this.imageView.setImageBitmap(bitmap);
            this.imageView.setVisibility(View.VISIBLE);
        }

        // Release reference to image view
        this.imageView = null;
    }
}
