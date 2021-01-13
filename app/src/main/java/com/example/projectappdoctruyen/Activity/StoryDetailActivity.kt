package com.example.projectappdoctruyen.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Activity.StoryDetailActivity
import com.example.projectappdoctruyen.Adapter.ChapterWatchedAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Service.CheckConnection
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils
import com.example.projectappdoctruyen.Utils.KeyIntent
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import java.util.*

class StoryDetailActivity : AppCompatActivity(), View.OnClickListener {
    private var toolbarStoryDetail: Toolbar? = null
    private var mDBHelper: DatabaseHelper? = null
    private var chapter: Chapter? = null
    private var textViewTitle: TextView? = null
    private var txtChapterStoryDetail: TextView? = null
    private var txtContentStory: TextView? = null
    private var txtNoReadStory: TextView? = null
    private var story: Story? = null
    private val chapterListLimit5: ArrayList<Chapter>? = null
    private var chapterWatchedAdapter: ChapterWatchedAdapter? = null
    private var recyclerView_ChapterWatched: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var imgStoryDetail: ImageView? = null
    private val chapterList: ArrayList<Chapter>? = null
    private var sharedPreferences_utils: SharedPreferences_Utils? = null
    private var templateView: TemplateView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)
        initView()
        dataIntentFromStoryId
        actionToolBar()
        initDataBase()
        setTextViewAndImageView()
        checkConnectAdsView()
    }

    private fun initDataBase() {
        mDBHelper = DatabaseHelper(this@StoryDetailActivity)
        chapter = mDBHelper!!.getChapterDefaultLimit1(story!!.id.toString() + "")
    }

    private fun checkConnectAdsView() {
        if (CheckConnection.isConnectedtoInternet(applicationContext)) {
            setAdsView()
        } else {
            templateView!!.visibility = View.GONE
        }
    }

    private fun setAdsView() {
        MobileAds.initialize(this@StoryDetailActivity) { }
        val builder = AdLoader.Builder(this@StoryDetailActivity, getString(R.string.Ads_appId))
        builder.forUnifiedNativeAd { unifiedNativeAd -> templateView!!.setNativeAd(unifiedNativeAd) }
        val adLoader = builder.build()
        val adRequest = AdRequest.Builder().build()
        adLoader.loadAd(adRequest)
    }

    private fun initView() {
        sharedPreferences_utils = SharedPreferences_Utils(this)
        templateView = findViewById(R.id.template_StoryDetailActivity)
        txtNoReadStory = findViewById(R.id.txtNoReadStory)
        txtContentStory = findViewById(R.id.txtContentStory)
        imgStoryDetail = findViewById(R.id.imgStoryDetail)
        recyclerView_ChapterWatched = findViewById(R.id.recyclerView_ChapterWatched)
        textViewTitle = findViewById(R.id.textViewTitle)
        txtChapterStoryDetail = findViewById(R.id.txtChapterStoryDetail)
        findViewById<View>(R.id.btnListChapters).setOnClickListener(this)
        findViewById<View>(R.id.btnFeedBackChapter).setOnClickListener(this)
        findViewById<View>(R.id.btnChapterRedStoryLimit1).setOnClickListener(this)
    }

    private fun setTextViewAndImageView() {
        textViewTitle!!.text = story!!.name
        txtContentStory!!.text = story!!.conTent
        val kq = story!!.id!! % 16
        val id = this.resources.getIdentifier(
                "com.example.projectappdoctruyen:drawable/s$kq", null, null)
        imgStoryDetail!!.setImageResource(id)
        mDBHelper = DatabaseHelper(this)
        val setCountStory = mDBHelper!!.getTaskCount(story!!.id!!)
        txtChapterStoryDetail!!.text = "$setCountStory Chương"
    }

    private val dataIntentFromStoryId: Unit
        private get() {
            val intent = intent
            if (intent != null) if (intent.hasExtra(KeyIntent.KEY_STORY)) {
                story = getIntent().getSerializableExtra(KeyIntent.KEY_STORY) as Story?
            }
        }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnListChapters -> {
                val intent = Intent(this@StoryDetailActivity, ChapterListsActivity::class.java)
                intent.putExtra("ChapterId", story)
                startActivity(intent)
            }
            R.id.btnFeedBackChapter -> {
                val intentACTION_VIEW = Intent(Intent.ACTION_VIEW)
                intentACTION_VIEW.data = Uri.parse("https://play.google.com/store/apps/details?id=com.zing.mp3&hl=vi")
                startActivity(intentACTION_VIEW)
            }
            R.id.btnChapterRedStoryLimit1 -> {
                val intentRedStoryLimit1 = Intent(this@StoryDetailActivity, ReadStoryActivity::class.java)
                intentRedStoryLimit1.putExtra("Chapter", chapter)
                startActivity(intentRedStoryLimit1)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val chapterListFromSharePre = sharedPreferences_utils!!.getDataSharePreferences_From_ChapterWatched(story)
        if (chapterListFromSharePre != null) {
            chapterWatchedAdapter = ChapterWatchedAdapter(this, chapterListFromSharePre)
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerView_ChapterWatched!!.layoutManager = linearLayoutManager
            recyclerView_ChapterWatched!!.setHasFixedSize(true)
            recyclerView_ChapterWatched!!.adapter = chapterWatchedAdapter
            chapterWatchedAdapter!!.notifyDataSetChanged()

        }
    }

    private fun actionToolBar() {
        toolbarStoryDetail = findViewById(R.id.toolbar_Truyen)
        setSupportActionBar(toolbarStoryDetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarStoryDetail!!.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val intent = Intent(this@StoryDetailActivity, SettingActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}