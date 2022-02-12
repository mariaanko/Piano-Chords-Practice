package com.wodpress.mariaanko.pianochordspractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ChordsAdapter.OnChordListener {

    private ArrayList<String> imagesArrayList = new ArrayList<>();
    ArrayList<String> imagesToLoadArray = new ArrayList<>();
    ChordsAdapter chordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(linearLayoutManager);
        String[] images = new String[0];
        try {
            images = this.getAssets().list("chords");
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagesArrayList = new ArrayList<>(Arrays.asList(images));
        imagesToLoadArray.addAll(imagesArrayList);
        chordsAdapter = new ChordsAdapter(MainActivity.this, imagesArrayList, this);
        recyclerView.setAdapter(chordsAdapter);

    }

    @Override
    public void onChordClick(int position, View view) {
        ImageView imageView = view.findViewById(R.id.image);
        if(!imagesToLoadArray.contains(imagesArrayList.get(position))){
            imagesToLoadArray.add(imagesArrayList.get(position));
            imageView.setBackgroundResource(0);
            imageView.setImageAlpha(1000);
        }else{
            imagesToLoadArray.remove(imagesArrayList.get(position));
            imageView.setBackgroundResource(R.drawable.unselected_foreground);
            imageView.setImageAlpha(90);
        }
    }
}