package com.wodpress.mariaanko.pianochordspractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ChordsAdapter extends RecyclerView.Adapter<ChordsAdapter.MyViewHolder> {

    ArrayList<String> images;
    Context context;
    CardView cardView;
    private OnChordListener onChordListener;

    public ChordsAdapter(Context context, ArrayList<String> images, OnChordListener onChordListener) {
        this.context = context;
        this.images = images;
        this.onChordListener = onChordListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(v, onChordListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            InputStream ims = context.getAssets().open("chords/" + images.get(position));
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            holder.image.setImageBitmap(bitmap);
            holder.image.setTag(position);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        OnChordListener onChordListener;
        public MyViewHolder(View itemView, OnChordListener onChordListener) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.onChordListener = onChordListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onChordListener.onChordClick(getAbsoluteAdapterPosition(), view);
        }
    }

    public interface OnChordListener{
        void onChordClick(int position, View view);
    }
}