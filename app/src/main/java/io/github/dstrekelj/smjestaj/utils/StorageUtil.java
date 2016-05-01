package io.github.dstrekelj.smjestaj.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Domagoj on 1.5.2016..
 *
 * Source: http://developer.android.com/reference/android/content/Context.html#getExternalFilesDir(java.lang.String)
 */
public class StorageUtil {
    public static final String TAG = StorageUtil.class.getSimpleName();

    public static void create(Context context, String filePath, String fileName) throws IOException {
        Log.d(TAG, "create");

        AssetManager assetManager = context.getAssets();

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] data = null;

        try {
            inputStream = assetManager.open(filePath);
            outputStream = new FileOutputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            outputStream.write(data);

            MediaScannerConnection.scanFile(
                    context,
                    new String[] { file.toString() },
                    null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.d(TAG, "Scanned " + path + ":");
                            Log.d(TAG, "-> uri=" + uri);
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void delete(Context context, String fileName) {
        Log.d(TAG, "delete");

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        if (file != null) {
            file.delete();
        }
    }

    public static boolean exists(Context context, String fileName) {
        Log.d(TAG, "exists");

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        return file != null && file.exists();
    }
}
