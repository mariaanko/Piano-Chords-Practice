package com.wodpress.mariaanko.pianochordspractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(linearLayoutManager);
        ChordsAdapter.ItemOffsetDecoration itemDecoration = new ChordsAdapter.ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        String[] images = new String[0];
        try {
            images = this.getAssets().list("chords");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> imagesArrayList = new ArrayList<>(Arrays.asList(images));
        ChordsAdapter customAdapter = new ChordsAdapter(MainActivity.this, imagesArrayList);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }
}