package com.example.projectappdoctruyen.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.StoryDetailActivity
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Utils.KeyIntent

class HistoryFragmentsAdapter(var context: Context?, var storyArrayList_Watched: List<Story?>?) : RecyclerView.Adapter<HistoryFragmentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doc_gan_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = storyArrayList_Watched!![position]
        holder.txtDocGandDay.text = story!!.name
        val kq = story!!.id!! % 16
        val id = context!!.resources.getIdentifier("com.example.projectappdoctruyen:drawable/s$kq", null, null)
        holder.imgHistoryStoryW.setImageResource(id)
    }

    override fun getItemCount(): Int {
        val getItemCountStoryWatched = storyArrayList_Watched!!.size
        return getItemCountStoryWatched
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtDocGandDay: TextView
        var imgHistoryStoryW: ImageView

        init {
            txtDocGandDay = itemView.findViewById(R.id.txtDocGandDay)
            imgHistoryStoryW = itemView.findViewById(R.id.imgHistoryStoryW)
            itemView.setOnClickListener {
                val intent = Intent(context, StoryDetailActivity::class.java)
                val keyPutIntent = storyArrayList_Watched!![position]
                intent.putExtra(KeyIntent.KEY_STORY, keyPutIntent)
                context!!.startActivity(intent)
            }
        }
    }
}