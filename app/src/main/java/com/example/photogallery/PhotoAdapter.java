package com.example.photogallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private List<GalleryItem> mDataSet;
    private Context context;

    public PhotoAdapter(List<GalleryItem> list, Context context) {
        mDataSet = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        return new PhotoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View view;
        private ImageView imageView;
        private GalleryItem mGalleryItem;


        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.item_image_view);
            imageView.setOnClickListener(this);
        }
        public void bind(GalleryItem item) {
            mGalleryItem = item;
            Picasso.get().load(item.getUrl()).into(imageView);
            imageView.setElevation((float)new Random().nextInt(5));
            imageView.setTranslationZ(imageView.getElevation());
        }

        @Override
        public void onClick(View v) {
            Intent i = PhotoPageActivity
                    .newIntent(context, mGalleryItem.getPhotoPageUri());
            context.startActivity(i);
        }
    }
}
