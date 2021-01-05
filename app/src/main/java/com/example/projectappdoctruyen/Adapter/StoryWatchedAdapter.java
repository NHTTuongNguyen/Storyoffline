package com.example.projectappdoctruyen.Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.projectappdoctruyen.Activity.StoryDetailActivity;
import com.example.projectappdoctruyen.Database.DatabaseHelper;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Utils.KeyIntent;

import java.util.List;

public class StoryWatchedAdapter extends RecyclerView.Adapter<StoryWatchedAdapter.StoryWatchedViewHolder> {
    Context context;
    List<Story> storyArrayList_Watched;


    public StoryWatchedAdapter(Context context, List<Story> storyArrayList_Watched) {
        this.context = context;
        this.storyArrayList_Watched = storyArrayList_Watched;
    }

    @NonNull
    @Override
    public StoryWatchedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_watched,parent,false);
        return new StoryWatchedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryWatchedViewHolder holder, int position) {
        Story story  = storyArrayList_Watched.get(position);
        holder.txtNameSory.setText(story.getName());
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        int setCountStory =  databaseHelper.getTaskCount(story.getId());
        holder.txtCountStoryWatched.setText(setCountStory+""+ " Chương");


        int kq = story.getId() %16;
        int id = context.getResources().getIdentifier("com.example.projectappdoctruyen:drawable/s" + kq, null, null);


//        int id =  Commons.setImageView(story.getId());


        holder.imgStoryWatched.setImageResource(id);

        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
    }

    @Override
    public int getItemCount() {
        int  getItemCountStoryWatched = storyArrayList_Watched.size();
        return getItemCountStoryWatched;
    }

    public class StoryWatchedViewHolder extends  RecyclerView.ViewHolder{
        public TextView txtNameSory,txtCountStoryWatched,txtTimeStoryWatched;
        public ImageView imgStoryWatched;
        public LinearLayout linearLayout;
        public StoryWatchedViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameSory = itemView.findViewById(R.id.txt_nameStoryWatched);
            txtCountStoryWatched = itemView.findViewById(R.id.txtCountStoryWatched);
            imgStoryWatched = itemView.findViewById(R.id.imgStoryWatched);
            linearLayout = itemView.findViewById(R.id.linearLayout_Story_Watched);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, StoryDetailActivity.class);
                    Story keyPutIntent = storyArrayList_Watched.get(getPosition());
                    intent.putExtra(KeyIntent.KEY_STORY,keyPutIntent);
                    Log.d("GGGG", String.valueOf(keyPutIntent.getId()+""+keyPutIntent.getName()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
