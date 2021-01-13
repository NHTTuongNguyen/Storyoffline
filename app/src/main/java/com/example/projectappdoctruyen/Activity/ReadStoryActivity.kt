package com.example.projectappdoctruyen.Activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.projectappdoctruyen.Adapter.ViewPagerReadStoryAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils
import com.google.android.gms.ads.AdView
import java.util.*

class ReadStoryActivity : AppCompatActivity() {
    var testToolBarId: Toolbar? = null
    var linearLayout_Visible: LinearLayout? = null
    private var chapter: Chapter? = null
    private var adView: AdView? = null
    private var sharedPreferencesUtils: SharedPreferences_Utils? = null
    private var viewPager: ViewPager? = null
    private var viewPagerReadStoryAdapter: ViewPagerReadStoryAdapter? = null
    private var chapterArrayList: List<Chapter?>? = null
    private var databaseHelper: DatabaseHelper? = null
    private var story: Story? = null
    private val storyArrayList: ArrayList<Story>? = null
    private var txtBack: TextView? = null
    private var txtCurrentNumber: TextView? = null
    private var txtTotalNumber: TextView? = null
    private var txtNext: TextView? = null
    lateinit var linearBottom : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_read_story)
        sharedPreferencesUtils = SharedPreferences_Utils(this)
        databaseHelper = DatabaseHelper(this)
        init()
        actionToolBar()
        dataIntent()
        chapterArrayList = databaseHelper!!.getChapTer(chapter!!.idStory.toString())
        val idStory = chapter!!.idStory
        story = databaseHelper!!.getStoryById(idStory.toString() + "")
        setAdapterViewPager()
        val index = indexChapter(chapterArrayList, chapter!!.idChapter!!)
        val indexsumone = index+1
        txtCurrentNumber!!.text = indexsumone.toString()

        txtTotalNumber!!.text = chapterArrayList!!.size.toString()
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                txtCurrentNumber!!.text = (position + 1).toString()
                if (position == 0) {
                    txtBack!!.visibility = View.GONE
                    txtNext!!.visibility = View.VISIBLE
                } else if (position == chapterArrayList!!.size - 1) {
                    txtBack!!.visibility = View.VISIBLE
                    txtNext!!.visibility = View.GONE
                } else {
                    txtNext!!.visibility = View.VISIBLE
                    txtBack!!.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        txtBack!!.setOnClickListener { viewPager!!.currentItem = viewPager!!.currentItem - 1 }
        txtNext!!.setOnClickListener { viewPager!!.currentItem = viewPager!!.currentItem + 1 }

        setOnclickList()
    }
    fun getViewPager(): ViewPager? {
        if (null == viewPager) {
            viewPager = findViewById<ViewPager>(R.id.fr_container_test)
        }
        return viewPager
    }
    private fun setOnclickList() {
        linearBottom.setOnClickListener {

//                showDialogNotification();
            val bottomSheet = ExampleBottomSheetDialog()
            bottomSheet.show(supportFragmentManager, "exampleBottomSheet")
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

    private fun setAdapterViewPager() {
        viewPagerReadStoryAdapter = ViewPagerReadStoryAdapter(supportFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, chapterArrayList)
        viewPager!!.adapter = viewPagerReadStoryAdapter
        var indexofchapter = -1
        for (i in chapterArrayList!!.indices) {
            if (chapterArrayList!![i]!!.idChapter == chapter!!.idChapter) {
                indexofchapter = i
            }
        }
        viewPager!!.currentItem = indexofchapter
    }

    private fun dataIntent() {
        val intent = intent
        if (intent != null) {
            chapter = intent.getSerializableExtra("Chapter") as Chapter?
        }
    }

    private fun actionToolBar() {
        testToolBarId = findViewById(R.id.testToolBarId)
        setSupportActionBar(testToolBarId)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        testToolBarId!!.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    private fun init() {
        viewPager = findViewById<ViewPager>(R.id.fr_container_test)
        linearBottom = findViewById(R.id.linearBottom)
        txtBack = findViewById(R.id.txtBack)
        txtCurrentNumber = findViewById(R.id.txtCurrentNumber)
        txtTotalNumber = findViewById(R.id.txtTotalNumber)
        txtNext = findViewById(R.id.txtNext)
        adView = findViewById(R.id.adView)
        linearLayout_Visible = findViewById(R.id.linearLayout_Visible)
    }
}