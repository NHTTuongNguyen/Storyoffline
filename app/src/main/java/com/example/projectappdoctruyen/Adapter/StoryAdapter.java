package com.example.projectappdoctruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.projectappdoctruyen.Interface.ItemClickListener;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Utils.KeyIntent;

import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ProductViewHolder> {
    Context context;
    List<Story> storyArrayList;
    DatabaseHelper databaseHelper;
    Story story;
    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public StoryAdapter(Context context, List<Story> storyArrayList) {
        this.context = context;
        this.storyArrayList = storyArrayList;
    }
    public StoryAdapter(List<Story> exampleList) {
        storyArrayList = exampleList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story,parent,false);
        return new ProductViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        story = storyArrayList.get(position);
        databaseHelper = new DatabaseHelper(context);
        int setCountStory =  databaseHelper.getTaskCount(story.getId());
        holder.txt_count.setText(setCountStory+ " Chương");
        holder.txt_nameStory.setText(story.getName());
        if (position % 2 == 0) {
            holder.txt_nameStory.setTextColor(Color.BLACK);
        }else{
            holder.txt_nameStory.setTextColor(context.getResources().getColor(R.color.colorGreen));
        }
        int kq = story.getId() %16;
        int id = context.getResources().getIdentifier("com.example.projectappdoctruyen:drawable/s" + kq, null, null);
        holder.imgStory.setImageResource(id);
//        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
    }

    @Override
    public int getItemCount() {
        return storyArrayList.size();
    }

    public class ProductViewHolder extends  RecyclerView.ViewHolder{
        public TextView txt_nameStory,txt_count;
        public ImageView imgStory;
        public LinearLayout linearLayout;
        public ProductViewHolder(@NonNull final View itemView,final OnItemClickListener listener) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout_StoryFragment);
            txt_nameStory = itemView.findViewById(R.id.txt_nameStory);
            txt_count = itemView.findViewById(R.id.txtChuongStory);
            imgStory = itemView.findViewById(R.id.imgStory);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, StoryDetailActivity.class);
                    Story keyPutIntent = storyArrayList.get(getPosition());
                    intent.putExtra(KeyIntent.KEY_STORY,keyPutIntent);
                    context.startActivity(intent);

                }
            });


        }


    }



    public void setFilter(ArrayList<Story>newList){
        storyArrayList = new ArrayList<>();
        storyArrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
