package com.example.projectappdoctruyen.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.ChapterListsActivity
import com.example.projectappdoctruyen.Adapter.ChapterAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import java.util.*

class ChapterListsActivity : AppCompatActivity() {
    private var story: Story? = null
    private var recyclerViewChapTer: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var chapterAdapter: ChapterAdapter? = null
    private var chapterList: List<Chapter?>? = null
    private var mDBHelper: DatabaseHelper? = null
    private var toolbar_ChapterStory: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapterlist)
        dataIntentFromStory
        actionToolBar()
        buildRecyclerViewChapList()
    }

    private fun buildRecyclerViewChapList() {
        recyclerViewChapTer = findViewById(R.id.recyclerViewChapTer)
        mDBHelper = DatabaseHelper(this@ChapterListsActivity)
        val idChapterList = story!!.id
        chapterList = mDBHelper!!.getChapTer(idChapterList.toString())
        chapterAdapter = ChapterAdapter(this, chapterList as ArrayList<Chapter?>?)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewChapTer!!.layoutManager = linearLayoutManager
        recyclerViewChapTer!!.setHasFixedSize(true)
        recyclerViewChapTer!!.adapter = chapterAdapter
        chapterAdapter!!.notifyDataSetChanged()
    }

    private fun actionToolBar() {
        toolbar_ChapterStory = findViewById(R.id.toolbar_ChapterStory)
        setSupportActionBar(toolbar_ChapterStory)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar_ChapterStory!!.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    private val dataIntentFromStory: Unit
        private get() {
            val intent = intent
            if (intent != null) {
                if (intent.hasExtra("ChapterId")) {
                    story = intent.getSerializableExtra("ChapterId") as Story?
                }
            }
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            val intent = Intent(this@ChapterListsActivity, SettingActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}