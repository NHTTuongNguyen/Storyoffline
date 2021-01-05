package com.example.projectappdoctruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectappdoctruyen.Activity.ReadStoryActivity;
import com.example.projectappdoctruyen.Fragment.ReadFragment;
import com.example.projectappdoctruyen.Model.Chapter;
import com.example.projectappdoctruyen.R;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ListStoryViewHolder> {

    Context context;
    List<Chapter> listChapter;
    Chapter chapter;

    public ChapterAdapter(Context context, List<Chapter> listStories) {
        this.context = context;
        this.listChapter = listStories;
    }

    @NonNull
    @Override
    public ListStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter,parent,false);
        return new ListStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStoryViewHolder holder, int position) {
       chapter = listChapter.get(position);
       holder.txtNameTitle.setText(chapter.getTitle());

       int kq = chapter.getIdStory() %16;
       int id = context.getResources().getIdentifier("com.example.projectappdoctruyen:drawable/s" + kq, null, null);

        Log.d("ChapterList",listChapter.get(position).getTitle());
       holder.imgChapterList.setImageResource(id);


//        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

    }

    @Override
    public int getItemCount() {
        int getItemCountListChapter = listChapter.size();
        Log.d("getItemCountListChapter",String.valueOf(getItemCountListChapter));
        return getItemCountListChapter;
    }

    public class ListStoryViewHolder extends  RecyclerView.ViewHolder{
        public TextView txtNameTitle;
        public ImageView imgChapterList;
        LinearLayout linearLayout;
        public ListStoryViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNameTitle = itemView.findViewById(R.id.txtTitle);
            imgChapterList = itemView.findViewById(R.id.imgChapterList);
            linearLayout = itemView.findViewById(R.id.linearLayout_ChapterList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ReadStoryActivity.class);
                    intent.putExtra("Chapter", listChapter.get(getPosition()));
                    Log.d("ChapterIDList",listChapter.get(getPosition()).getIdChapter()+"");
                    context.startActivity(intent);
                }
            });
        }
    }



}
