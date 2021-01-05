package com.example.projectappdoctruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectappdoctruyen.Activity.StoryDetailActivity;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Utils.KeyIntent;

import java.util.List;

public class HistoryFragmentsAdapter extends RecyclerView.Adapter<HistoryFragmentsAdapter.ViewHolder>  {
    Context context;
    List<Story> storyArrayList_Watched;
    public HistoryFragmentsAdapter(Context context, List<Story> storyArrayList_Watched) {
        this.context = context;
        this.storyArrayList_Watched = storyArrayList_Watched;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doc_gan_day,parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story  = storyArrayList_Watched.get(position);
        holder.txtDocGandDay.setText(story.getName());
        int kq = story.getId() %16;
        int id = context.getResources().getIdentifier("com.example.projectappdoctruyen:drawable/s" + kq, null, null);
        holder.imgHistoryStoryW.setImageResource(id);
    }
    @Override
    public int getItemCount() {
        int  getItemCountStoryWatched = storyArrayList_Watched.size();
        Log.d("getItemCountStoryWatched",getItemCountStoryWatched+"");
        return getItemCountStoryWatched;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtDocGandDay;
        public ImageView imgHistoryStoryW;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDocGandDay = itemView.findViewById(R.id.txtDocGandDay);
            imgHistoryStoryW = itemView.findViewById(R.id.imgHistoryStoryW);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, StoryDetailActivity.class);
                    Story keyPutIntent = storyArrayList_Watched.get(getPosition());
                    intent.putExtra(KeyIntent.KEY_STORY,keyPutIntent);
                    context.startActivity(intent);
                }
            });
        }
    }
}
