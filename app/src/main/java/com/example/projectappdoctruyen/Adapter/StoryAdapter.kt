package com.example.projectappdoctruyen.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.StoryDetailActivity
import com.example.projectappdoctruyen.Adapter.StoryAdapter.ProductViewHolder
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Interface.ItemClickListener
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Utils.KeyIntent
import java.util.*

class StoryAdapter : RecyclerView.Adapter<ProductViewHolder> {
    var context: Context? = null
    var storyArrayList: MutableList<Story>
    var databaseHelper: DatabaseHelper? = null
    var story: Story? = null
    private var itemClickListener: ItemClickListener? = null
    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    constructor(context: Context?, storyArrayList: MutableList<Story>) {
        this.context = context
        this.storyArrayList = storyArrayList
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return ProductViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        story = storyArrayList!![position]
        databaseHelper = DatabaseHelper(context)
        val setCountStory = databaseHelper!!.getTaskCount(story!!.id!!)
        holder.txt_count.text = "$setCountStory Chương"
        holder.txt_nameStory.text = story!!.name
        if (position % 2 == 0) {
            holder.txt_nameStory.setTextColor(Color.BLACK)
        } else {
            holder.txt_nameStory.setTextColor(context!!.resources.getColor(R.color.colorGreen))
        }
        val kq = story!!.id!! % 16
        val id = context!!.resources.getIdentifier("com.example.projectappdoctruyen:drawable/s$kq", null, null)
        holder.imgStory.setImageResource(id)
        //        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
    }

    override fun getItemCount(): Int {
        return storyArrayList!!.size
    }

    inner class ProductViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var txt_nameStory: TextView
        var txt_count: TextView
        var imgStory: ImageView
        var linearLayout: LinearLayout

        init {
            linearLayout = itemView.findViewById(R.id.linearLayout_StoryFragment)
            txt_nameStory = itemView.findViewById(R.id.txt_nameStory)
            txt_count = itemView.findViewById(R.id.txtChuongStory)
            imgStory = itemView.findViewById(R.id.imgStory)
            itemView.setOnClickListener {
                val intent = Intent(context, StoryDetailActivity::class.java)
                val keyPutIntent = storyArrayList!![position]
                intent.putExtra(KeyIntent.KEY_STORY, keyPutIntent)
                context!!.startActivity(intent)
            }
        }
    }

    fun setFilter(newList: MutableList<Story>) {
        storyArrayList = ArrayList()
        storyArrayList!!.addAll(newList)
        notifyDataSetChanged()
    }
}