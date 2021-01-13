package com.example.projectappdoctruyen.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.ReadStoryActivity
import com.example.projectappdoctruyen.Adapter.ChapterAdapter.ListChapterViewHolderItem
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.R

class ChapterAdapter(var context: Context, var listChapter: List<Chapter?>?) : RecyclerView.Adapter<ListChapterViewHolderItem>() {
    var chapter: Chapter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListChapterViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ListChapterViewHolderItem(view)
    }

    override fun onBindViewHolder(holderListChapterViewHolderItem: ListChapterViewHolderItem, position: Int) {
        chapter = listChapter!![position]
        holderListChapterViewHolderItem.txtNameTitle.text = chapter!!.title
        val kq = chapter!!.idStory!! % 16

        val id = context.resources.getIdentifier("com.example.projectappdoctruyen:drawable/s$kq", null, null)
        holderListChapterViewHolderItem.imgChapterList.setImageResource(id)


//        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
    }

    override fun getItemCount(): Int {
        val getItemCountListChapter = listChapter!!.size
        Log.d("getItemCountListChapter", getItemCountListChapter.toString())
        return getItemCountListChapter
    }

    inner class ListChapterViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNameTitle: TextView = itemView.findViewById(R.id.txtTitle)
        var imgChapterList: ImageView = itemView.findViewById(R.id.imgChapterList)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout_ChapterList)

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, ReadStoryActivity::class.java)
                intent.putExtra("Chapter", listChapter!![position])
                context.startActivity(intent)
            }
        }
    }
}