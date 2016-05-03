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
 * Utility class for storing asset files to external files directory.
 *
 * Source: http://developer.android.com/reference/android/content/Context.html#getExternalFilesDir(java.lang.String)
 */
public class StorageUtil {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = StorageUtil.class.getSimpleName();

    /**
     * Creates new copy of file at `filePath` of name `fileName` in the external files directory for
     * pictures that is available to `context`.
     *
     * @param context   Context of invocation
     * @param filePath  Path to asset file
     * @param fileName  Destination file name
     * @throws IOException
     */
    public static void create(Context context, String filePath, String fileName) {
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
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes `fileName` file from external files directory for pictures that is available to
     * `context`.
     *
     * @param context   Context of invocation
     * @param fileName  File name
     */
    public static void delete(Context context, String fileName) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        if (file != null) {
            file.delete();
        }
    }

    /**
     * Checks if `filename` file exists in external files directory for pictures that is available
     * to `context`.
     *
     * @param context   Context of invocation
     * @param fileName  File name
     * @return  `true` if exists, `false` if not
     */
    public static boolean exists(Context context, String fileName) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        return file != null && file.exists();
    }
}
