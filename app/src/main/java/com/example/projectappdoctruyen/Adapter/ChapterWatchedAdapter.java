package com.example.projectappdoctruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectappdoctruyen.Activity.ReadStoryActivity;
import com.example.projectappdoctruyen.Model.Chapter;
import com.example.projectappdoctruyen.R;

import java.util.ArrayList;

public class ChapterWatchedAdapter extends RecyclerView.Adapter<ChapterWatchedAdapter.ViewHolder>  {
    Context context;
    ArrayList<Chapter> chapterArrayList_Wathched;

    public ChapterWatchedAdapter(Context context, ArrayList<Chapter> chapterArrayList_Wathched) {
        this.context = context;
        this.chapterArrayList_Wathched = chapterArrayList_Wathched;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter_watched,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter = chapterArrayList_Wathched.get(position);
        holder.txtChapterWatched.setText((chapter.getTitle()));

    }
    @Override
    public int getItemCount() {
        return chapterArrayList_Wathched.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtChapterWatched;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtChapterWatched = itemView.findViewById(R.id.txtChapterWatched);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ReadStoryActivity.class);
                    intent.putExtra("Chapter", chapterArrayList_Wathched.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
