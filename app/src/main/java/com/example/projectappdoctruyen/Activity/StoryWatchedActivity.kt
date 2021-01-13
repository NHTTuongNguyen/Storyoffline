package com.example.projectappdoctruyen.Activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Adapter.StoryWatchedAdapter
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils
import java.util.*

class StoryWatchedActivity : AppCompatActivity() {
    private var toolbar_StoryWatched: Toolbar? = null
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var storyAdapter: StoryWatchedAdapter? = null
    private var storyList: ArrayList<Story?>? = null
    private var txtNodataStoryWatched: TextView? = null
    private var sharedPreferencesUtils: SharedPreferences_Utils? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_watched)
        sharedPreferencesUtils = SharedPreferences_Utils(this)
        storyList = sharedPreferencesUtils!!.get_SharedPreferences_Story_HistoryFragment()
        //        SettingSharedPreferences.getsharedPre_StoryWatcheds(storyList);
        init()
        actionToolBar(toolbar_StoryWatched)
        buildRecyclerView()
    }

    private fun buildRecyclerView() {
        if (storyList!!.size > 0) {
            storyAdapter = StoryWatchedAdapter(this, storyList)
            linearLayoutManager = LinearLayoutManager(this)
            recyclerView!!.layoutManager = linearLayoutManager
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.adapter = storyAdapter
            storyAdapter!!.notifyDataSetChanged()
            recyclerView!!.visibility = View.VISIBLE
            txtNodataStoryWatched!!.visibility = View.GONE
        } else {
            recyclerView!!.visibility = View.GONE
            txtNodataStoryWatched!!.visibility = View.VISIBLE
        }
    }

    private fun init() {
        txtNodataStoryWatched = findViewById(R.id.txtNodataStoryWatched)
        toolbar_StoryWatched = findViewById(R.id.toolbar_StoryWatched)
        recyclerView = findViewById(R.id.recyclerView_Watched)
    }

    private fun actionToolBar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setNavigationOnClickListener { finish() }
    }
}