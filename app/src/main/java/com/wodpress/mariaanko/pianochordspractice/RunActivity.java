package com.wodpress.mariaanko.pianochordspractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class RunActivity extends AppCompatActivity {

    ArrayList<String> imageItemsList = new ArrayList<>();
    final Random random = new Random();
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        imageItemsList = (ArrayList<String>) getIntent().getSerializableExtra("items");

        mImageView = findViewById(R.id.randomImageView);

        AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
        ArrayList<String> intervalList = new ArrayList<>(R.array.interval);

        builder.setTitle("Pick interval (seconds)")
                .setItems(R.array.interval, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                InputStream ims = null;
                                try {
                                    ims = getBaseContext()
                                            .getAssets()
                                            .open("chords/" + imageItemsList.get(random.nextInt(imageItemsList.size())));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Bitmap bitmap = BitmapFactory.decodeStream(ims);
                                mImageView.setImageBitmap(bitmap);
                                handler.postDelayed(this, (Integer.getInteger(intervalList.get(which))) * 1000);
                            }
                        }, (Integer.getInteger(intervalList.get(which))) * 1000);
                    }
                });
        builder.create();
        builder.show();

    }
}