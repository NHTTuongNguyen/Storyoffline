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
import com.example.projectappdoctruyen.Activity.ExampleBottomSheetDialog
import com.example.projectappdoctruyen.Activity.HomeActivity
import com.example.projectappdoctruyen.Activity.ReadStoryActivity
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.R

class ChapterListBottomItemAdapter(var context: Context, var listChapter: List<Chapter?>?,var exampleBottomSheetDialog: ExampleBottomSheetDialog): RecyclerView.Adapter<ChapterListBottomItemAdapter.ListChapterViewHolderItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListChapterViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistchapterbottom, parent, false)
        return ListChapterViewHolderItem(view)

    }

    override fun onBindViewHolder(holder: ListChapterViewHolderItem, position: Int) {
       val  chapter = listChapter!![position]
        holder.txtitemchapter_number.text = chapter!!.idChapter.toString()

    }

    override fun getItemCount(): Int {
        val getItemCountListChapter = listChapter!!.size
        Log.d("getItemCountListChapter", getItemCountListChapter.toString())
        return getItemCountListChapter
    }

    inner class ListChapterViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtitemchapter_number: TextView = itemView.findViewById(R.id.txtitemchapter_number)


        init {
            itemView.setOnClickListener {
//                val intent = Intent(context, ReadStoryActivity::class.java)
//                intent.putExtra("Chapter", listChapter!![position])
//                context.startActivity(intent)
                val  chapter = listChapter!![position]
                val index = indexChapter(listChapter,chapter!!.idChapter!!)
                (context as ReadStoryActivity?)!!.getViewPager()!!.currentItem = index
                exampleBottomSheetDialog.dismiss()

            }
        }
    }

    private fun indexChapter(list: List<Chapter?>?, id: Int): Int {
        for (i in list!!.indices) {
            if (list[i]!!.idChapter == id) {
                return i
            }
        }
        return -1
    }


}