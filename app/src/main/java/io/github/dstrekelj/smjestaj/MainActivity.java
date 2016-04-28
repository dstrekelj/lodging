package io.github.dstrekelj.smjestaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import io.github.dstrekelj.smjestaj.activities.ItemActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream stream = null;
        try {
            stream = getResources().getAssets().open("lodgings.json");
            Scanner scanner = new Scanner(stream);
            Log.d("json", scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, ItemActivity.class);
        startActivity(i);
    }
}
