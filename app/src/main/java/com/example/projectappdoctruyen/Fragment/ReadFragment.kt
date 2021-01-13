package com.example.projectappdoctruyen.Fragment

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.projectappdoctruyen.Activity.ReadStoryActivity
import com.example.projectappdoctruyen.Activity.SettingActivity
import com.example.projectappdoctruyen.Adapter.ViewPagerReadStoryAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils
import com.example.projectappdoctruyen.Utils.KeyIntent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*


class ReadFragment : Fragment(), View.OnClickListener {
    private var txtReadStory: TextView? = null
    var chapter: Chapter? = null
    private var linearLayout_Visible: LinearLayout? = null
    private val objectAnimator: ObjectAnimator? = null
    private val relativeLayout: RelativeLayout? = null
    private var relativeLayoutReadStory: RelativeLayout? = null
    private var sharedPreferencesUtils: SharedPreferences_Utils? = null
    private var typeface: Typeface? = null
    private var scrollView: NestedScrollView? = null
    private val img_MenuStory: ImageView? = null
    private var image_Pause: ImageView? = null
    private val img_SettingReadStory: ImageView? = null
    private var seekBar_ReadStory: SeekBar? = null
    private var chapterArrayList: List<Chapter?>? = null
    private var view1: View? = null
    private var databaseHelper: DatabaseHelper? = null
    private val viewPagerReadStoryAdapter: ViewPagerReadStoryAdapter? = null
    private var visible_relativeLayoutLayoutClick = false
    var play = false
    private var handler: Handler? = null
    private var timerTask: TimerTask? = null
    private var scrollTimer: Timer? = null
    private var adView: AdView? = null
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view1 = inflater.inflate(R.layout.fragment_read, container, false)
        initView()
        dataIntentFromChapter
        eventSeekBarReadStory()
        //        readStory();
        chapterArrayList = databaseHelper!!.getChapTer(chapter!!.idStory.toString())
        setEventScrollProgress()
        loadAdsBannerView()
        return view1
    }

    private fun initView() {
        sharedPreferencesUtils = SharedPreferences_Utils(requireActivity())
        //        viewPagerReadStoryAdapter = new ViewPagerReadStoryAdapter(getActivity().getSupportFragmentManager(),chapterArrayList,chapter);
        databaseHelper = DatabaseHelper(activity)
        txtReadStory = view1!!.findViewById(R.id.textviewReadStory)
        relativeLayoutReadStory = view1!!.findViewById(R.id.relativeLayoutRedStory)
        scrollView = view1!!.findViewById(R.id.scrollView_ReadStory)
        seekBar_ReadStory = view1!!.findViewById(R.id.seekBar_ReadStoryActivity)
        image_Pause = view1!!.findViewById(R.id.image_Pause)
        linearLayout_Visible = view1!!.findViewById(R.id.linearLayout_Visible)
        view1!!.findViewById<View>(R.id.img_SettingReadStory).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.image_Pause).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.img_MenuStory).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.relativeLayoutLayoutClick).setOnClickListener(this)
    }

    private fun loadAdsBannerView() {
        adView = view1!!.findViewById(R.id.adViewReadFragment)
        MobileAds.initialize(activity, "ca-app-pub-5478028523793903/9984628664")
        val adRequest = AdRequest.Builder().build()
        adView!!.loadAd(adRequest)
    }

    private fun setEventScrollProgress() {
        scrollView!!.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = scrollView!!.scrollY
            if (scrollY >= 100) {
                sharedPreferencesUtils!!.setDataSharePreferences_From_Chapter(chapter)
            }
            val textViewHeight = txtReadStory!!.height
            seekBar_ReadStory!!.progress = scrollY * 100 / textViewHeight
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_SettingReadStory -> goToSettingActivity()
            R.id.image_Pause -> eventPlayorPause()
            R.id.img_MenuStory -> requireActivity().finish()
            R.id.relativeLayoutLayoutClick -> eventVisibleViewSetting()
        }
    }

    private fun eventVisibleViewSetting() {
        visible_relativeLayoutLayoutClick = !visible_relativeLayoutLayoutClick
        if (visible_relativeLayoutLayoutClick) {
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            (activity as ReadStoryActivity?)!!.testToolBarId!!.visibility = View.VISIBLE
            linearLayout_Visible!!.visibility = View.VISIBLE
            adView!!.visibility = View.GONE
        } else {
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            (activity as ReadStoryActivity?)!!.testToolBarId!!.visibility = View.GONE
            linearLayout_Visible!!.visibility = View.GONE
            adView!!.visibility = View.VISIBLE
        }
    }

    private fun eventPlayorPause() {
        play = !play
        if (play) {
            image_Pause!!.setImageResource(R.drawable.ic_baseline_play_24)
//            autoScrollViewVersiton2()
        } else {
            image_Pause!!.setImageResource(R.drawable.ic_baseline_pause_24)
            if (timerTask != null) {
                timerTask!!.cancel()
            }
        }
    }

    private fun autoScrollViewVersiton2() {
        scrollTimer = Timer()
        handler = Handler()
        timerTask = object : TimerTask() {
            override fun run() {
                handler!!.post {
//                    val moveScrollView = MoveScrollView()
//                    moveScrollView.execute()
                }
            }
        }
        scrollTimer!!.schedule(timerTask, 0, 10)
    }

//    private class MoveScrollView : AsyncTask<Void?, Void?, Void?>() {
//        override fun doInBackground(vararg p0: Void?): Void? {
//            val seekBar_UserClick: Int =sharedPreferencesUtils!!.getSeeBar();
//            scrollView.smoothScrollTo(0, (scrollView.getScrollY() + seekBar_UserClick))
//            return null
//        }
//
//        override fun onProgressUpdate(vararg values: Void?) {
//            super.onProgressUpdate(*values)
//        }
//
//    }

    private fun goToSettingActivity() {
        val intent = Intent(activity, SettingActivity::class.java)
        if (timerTask != null) {
            timerTask!!.cancel()
        }
        image_Pause!!.setImageResource(R.drawable.ic_baseline_pause_24)
        startActivity(intent)
    }

    private fun eventSeekBarReadStory() {
        seekBar_ReadStory = view1!!.findViewById(R.id.seekBar_ReadStoryActivity)
        seekBar_ReadStory!!.setProgress(0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar_ReadStory!!.setMin(0)
        }
        seekBar_ReadStory!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var seekBarProgress = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                seekBarProgress = progress
                Log.d("QQQQQQQ", seekBarProgress.toString() + "")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val textViewHeight = txtReadStory!!.height
                scrollView!!.post {
                    image_Pause!!.setImageResource(R.drawable.ic_baseline_pause_24)
                    val scrollViewForText = textViewHeight * seekBarProgress / 100
                    scrollView!!.scrollTo(0, scrollViewForText)
                    if (timerTask != null) {
                        timerTask!!.cancel()
                    }
                }
            }
        })
    }

    private val dataIntentFromChapter: Unit
        private get() {
            val bundle = arguments
            if (bundle != null) {
                chapter = bundle["KEY"] as Chapter?
                if (chapter != null) {
                    txtReadStory!!.text = Html.fromHtml(chapter!!.detail)
                }
            }
        }



    override fun onResume() {
        super.onResume()
        ////////setBackground
        relativeLayoutReadStory!!.setBackgroundColor(sharedPreferencesUtils!!.getColorBackgroundReadStory())
        ////////setScreenTimeOut
        setScreenTimeout(sharedPreferencesUtils!!.getScreenTimeOutReadStory())
        ////setTextSize
        txtReadStory!!.textSize = sharedPreferencesUtils!!.getTextSizeReadStory()
        //////setFontStyle
//        typeface = getResources().getFont(settingSharedPreferences.getFontReadStory());
        typeface = ResourcesCompat.getFont(requireActivity(), sharedPreferencesUtils!!.getFontReadStory())
        txtReadStory!!.typeface = typeface
        ///setLineHeight
        txtReadStory!!.setLineSpacing(sharedPreferencesUtils!!.getLineHeight(), 1f)
        ////setTextcolor
        txtReadStory!!.setTextColor(sharedPreferencesUtils!!.getChangeTextColor())
        //        image_Pause.setImageResource(R.drawable.ic_baseline_pause_24);
    }

    private fun setScreenTimeout(millisecounds: Int) {
//        android.provider.Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT,millisecounds);
        Settings.System.getInt(requireActivity().contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, millisecounds)
    }

    companion object {
        private const val idAdView = "723868771524475_723869634857722"
        fun newInstance(sectionNumber: Int): ReadFragment {
            val fragment = ReadFragment()
            val args = Bundle()
            args.putInt(KeyIntent.ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}