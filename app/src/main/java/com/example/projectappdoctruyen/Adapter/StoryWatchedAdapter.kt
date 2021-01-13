package com.example.projectappdoctruyen.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.StoryDetailActivity
import com.example.projectappdoctruyen.Adapter.StoryWatchedAdapter.StoryWatchedViewHolder
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Utils.KeyIntent

class StoryWatchedAdapter(var context: Context, var storyArrayList_Watched: List<Story?>?) : RecyclerView.Adapter<StoryWatchedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryWatchedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_watched, parent, false)
        return StoryWatchedViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryWatchedViewHolder, position: Int) {
        val story = storyArrayList_Watched!![position]
        holder.txtNameSory.text = story!!.name
        val databaseHelper = DatabaseHelper(context)
        val setCountStory = databaseHelper.getTaskCount(story!!.id!!)
        holder.txtCountStoryWatched.text = "$setCountStory Chương"
        val kq = story!!.id!! % 16
        val id = context.resources.getIdentifier("com.example.projectappdoctruyen:drawable/s$kq", null, null)


//        int id =  Commons.setImageView(story.getId());
        holder.imgStoryWatched.setImageResource(id)
        holder.linearLayout.animation = AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)
    }

    override fun getItemCount(): Int {
        return storyArrayList_Watched!!.size
    }

    inner class StoryWatchedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNameSory: TextView
        var txtCountStoryWatched: TextView
        var txtTimeStoryWatched: TextView? = null
        var imgStoryWatched: ImageView
        var linearLayout: LinearLayout

        init {
            txtNameSory = itemView.findViewById(R.id.txt_nameStoryWatched)
            txtCountStoryWatched = itemView.findViewById(R.id.txtCountStoryWatched)
            imgStoryWatched = itemView.findViewById(R.id.imgStoryWatched)
            linearLayout = itemView.findViewById(R.id.linearLayout_Story_Watched)
            itemView.setOnClickListener {
                val intent = Intent(context, StoryDetailActivity::class.java)
                val keyPutIntent = storyArrayList_Watched!![position]
                intent.putExtra(KeyIntent.KEY_STORY, keyPutIntent)
                context.startActivity(intent)
            }
        }
    }
}