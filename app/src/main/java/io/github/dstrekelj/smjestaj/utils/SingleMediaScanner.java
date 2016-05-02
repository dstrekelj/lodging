package io.github.dstrekelj.smjestaj.utils;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.File;

/**
 * Created by Domagoj on 2.5.2016..
 */
public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
    public static final String TAG = SingleMediaScanner.class.getSimpleName();

    private Context context;
    private MediaScannerConnection mediaScannerConnection;
    private File file;

    public SingleMediaScanner(Context context, File file) {
        this.context = context;
        this.file = file;
        this.mediaScannerConnection = new MediaScannerConnection(context, this);
        this.mediaScannerConnection.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        mediaScannerConnection.scanFile(file.getAbsolutePath(), null);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        this.context.startActivity(intent);
        mediaScannerConnection.disconnect();
    }
}
