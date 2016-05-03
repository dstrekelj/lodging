package io.github.dstrekelj.smjestaj.utils;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.File;

/**
 * Runs media scanner for single media file.
 */
public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = SingleMediaScanner.class.getSimpleName();

    /**
     * Reference to context scanner was invoked from.
     */
    private Context context;

    /**
     * Media scanner connection object.
     */
    private MediaScannerConnection mediaScannerConnection;

    /**
     * Reference to file to be scanned.
     */
    private File file;

    /**
     * Constructor.
     *
     * @param context   Context
     * @param file      File to scan
     */
    public SingleMediaScanner(Context context, File file) {
        this.context = context;
        this.file = file;
        this.mediaScannerConnection = new MediaScannerConnection(context, this);
        this.mediaScannerConnection.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        this.mediaScannerConnection.scanFile(file.getAbsolutePath(), null);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        this.context.startActivity(intent);
        this.mediaScannerConnection.disconnect();
    }
}
