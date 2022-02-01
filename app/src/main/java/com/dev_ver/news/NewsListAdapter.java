package com.dev_ver.news;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private ArrayList<NewsComp.NewsComponent> content;
    public NewsListAdapter(ArrayList<NewsComp.NewsComponent> items) {
        content=items;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View item_view=inflator.inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(item_view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsComp.NewsComponent currentItem=content.get(position);
        Bitmap bitmap=null;
        holder.title.setText(currentItem.title);
        holder.description.setText(currentItem.description);
        holder.author.setText(currentItem.author);
        dashboard.BackgroundImage getImage=new dashboard.BackgroundImage();

        try {
            bitmap=getImage.execute(currentItem.imageUrl).get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        holder.image.setImageBitmap(bitmap);
        //Glide.with(holder.itemView.getContext()).load(currentItem.imageUrl).into(holder.image);
    }
    public void updateNews(ArrayList<NewsComp.NewsComponent> updatedNews)
    {
        content.clear();
        content.addAll(updatedNews);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return content.size();
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder {
     TextView title;
     TextView description;
     ImageView image;
     TextView author;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
       title=itemView.findViewById(R.id.title);
       description=itemView.findViewById(R.id.description);
       image=itemView.findViewById(R.id.image);
       author=itemView.findViewById(R.id.author);
    }
}
