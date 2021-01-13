package com.example.projectappdoctruyen.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.ReadStoryActivity
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.R
import java.util.*

class ChapterWatchedAdapter(var context: Context, var chapterArrayList_Wathched: ArrayList<Chapter?>) : RecyclerView.Adapter<ChapterWatchedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter_watched, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapterArrayList_Wathched[position]
        holder.txtChapterWatched.text = chapter!!.title
    }

    override fun getItemCount(): Int {
        return chapterArrayList_Wathched.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtChapterWatched: TextView

        init {
            txtChapterWatched = itemView.findViewById(R.id.txtChapterWatched)
            itemView.setOnClickListener {
                val intent = Intent(context, ReadStoryActivity::class.java)
                intent.putExtra("Chapter", chapterArrayList_Wathched[position])
                context.startActivity(intent)
            }
        }
    }
}