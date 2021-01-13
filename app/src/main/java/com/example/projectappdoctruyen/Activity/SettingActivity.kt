package com.example.projectappdoctruyen.Activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils
import com.example.projectappdoctruyen.Utils.TextSize

class SettingActivity : AppCompatActivity(), View.OnClickListener {
    private var toolbar_Setting: Toolbar? = null
    private var nestedScrollView: NestedScrollView? = null
    private var sharedPreferencesUtils: SharedPreferences_Utils? = null
    private val btn_position_change_background = arrayOfNulls<Button>(3)
    private var button_change_background: Button? = null
    private val btn_id_change_background = intArrayOf(
            R.id.btntrang,
            R.id.btnden,
            R.id.btnvang)
    private val btn_position_change_screen_time_out = arrayOfNulls<Button>(4)
    private var button_change_screen_time_out: Button? = null
    private val btn_id_change_screen_time_out = intArrayOf(
            R.id.btnmanghinhsang30s,
            R.id.btnmanghinhsang1p,
            R.id.btnmanghinhsang10phut,
            R.id.btnmanghinhsang30phut
    )
    private val btn_position_change_text_size = arrayOfNulls<Button>(6)
    private var button_change_text_size: Button? = null
    private val btn_id_change_text_size = intArrayOf(
            R.id.btnSize12,
            R.id.btnSize14,
            R.id.btnSize16,
            R.id.btnSize18,
            R.id.btnSize20,
            R.id.btnSize22
    )
    private val btn_position_change_font_style = arrayOfNulls<Button>(5)
    private var button_change_font_style: Button? = null
    private val btn_id_change_font_style = intArrayOf(
            R.id.btnFontimesnewroman,
            R.id.btnFontserif,
            R.id.btnFontbookerly,
            R.id.btnFonthelvetica,
            R.id.btnFontlora
    )
    private val btn_position_change_read_style = arrayOfNulls<Button>(3)
    private var button_change_read_style: Button? = null
    private val btn_id_change_read_style = intArrayOf(
            R.id.btncuondoc,
            R.id.btnlattrang,
            R.id.btnkkethop)
    private val btn_position_change_line_height = arrayOfNulls<Button>(6)
    private var button_change_line_height: Button? = null
    private val btn_id_change_line_height = intArrayOf(
            R.id.btnlineHeight_1,
            R.id.btnlineHeight_2,
            R.id.btnlineHeight_3,
            R.id.btnlineHeight_4,
            R.id.btnlineHeight_5,
            R.id.btnlineHeight_6)
    private val btn_position_change_auto_scroll = arrayOfNulls<Button>(2)
    private var button_change_auto_scroll: Button? = null
    private val btn_id_change_auto_scroll = intArrayOf(
            R.id.btnautoscroll_No,
            R.id.btnautoscroll_Yes)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        init()
        actionToolBar()
        sharedPreferencesUtils = SharedPreferences_Utils(this)
        eventSeeBarSetting()
        initGroupButtonChangeBackground()
        idButtonChangeBackgroundClick
        ///////////////////////////////////////////
        initGroupButtonChangeScreenTimeOut()
        idButtonChangeScreenTimeOutClick
        ///////////////////////////////////////////////////
        initGroupButtonChangeTextSize()
        idButtonChangeTextSize
        /////////////////////////
        initGroupButtonChangeFontStyle()
        idButtonChangeFontStyle
        /////////////////////////
        initGroupButtonChangeReadStyle()
        idButtonChangeReadStyle

        //////////////////
        initGroupButtonChangeLineHeight()
        idButtonChangeLineHeight

        /////////////////////
        initGroupButtonChangeAutoScroll()
        idButtonChangeAutoScroll
    }

    private fun initGroupButtonChangeAutoScroll() {
        for (i in btn_position_change_auto_scroll.indices) {
            btn_position_change_auto_scroll[i] = findViewById<View>(btn_id_change_auto_scroll[i]) as Button
            btn_position_change_auto_scroll[i]!!.setOnClickListener(this)
        }
        button_change_auto_scroll = btn_position_change_auto_scroll[0]
    }

    private fun initGroupButtonChangeLineHeight() {
        for (i in btn_position_change_line_height.indices) {
            btn_position_change_line_height[i] = findViewById<View>(btn_id_change_line_height[i]) as Button
            btn_position_change_line_height[i]!!.setOnClickListener(this)
        }
        button_change_line_height = btn_position_change_line_height[0]
    }

    private fun initGroupButtonChangeReadStyle() {
        for (i in btn_position_change_read_style.indices) {
            btn_position_change_read_style[i] = findViewById<View>(btn_id_change_read_style[i]) as Button
            btn_position_change_read_style[i]!!.setOnClickListener(this)
        }
        button_change_read_style = btn_position_change_read_style[0]
    }

    private fun initGroupButtonChangeFontStyle() {
        for (i in btn_position_change_font_style.indices) {
            btn_position_change_font_style[i] = findViewById<View>(btn_id_change_font_style[i]) as Button
            btn_position_change_font_style[i]!!.setOnClickListener(this)
        }
        button_change_font_style = btn_position_change_font_style[0]
    }

    private fun initGroupButtonChangeTextSize() {
        for (i in btn_position_change_text_size.indices) {
            btn_position_change_text_size[i] = findViewById<View>(btn_id_change_text_size[i]) as Button
            btn_position_change_text_size[i]!!.setOnClickListener(this)
        }
        button_change_text_size = btn_position_change_text_size[0]
    }

    private fun initGroupButtonChangeScreenTimeOut() {
        for (i in btn_position_change_screen_time_out.indices) {
            btn_position_change_screen_time_out[i] = findViewById<View>(btn_id_change_screen_time_out[i]) as Button
            btn_position_change_screen_time_out[i]!!.setOnClickListener(this)
        }
        button_change_screen_time_out = btn_position_change_screen_time_out[0]
    }

    private fun initGroupButtonChangeBackground() {
        for (i in btn_position_change_background.indices) {
            btn_position_change_background[i] = findViewById<View>(btn_id_change_background[i]) as Button
            btn_position_change_background[i]!!.setOnClickListener(this)
        }
        button_change_background = btn_position_change_background[0]
    }

    private val idButtonChangeBackgroundClick: Unit
        private get() {
            val idButton: Int = sharedPreferencesUtils!!.getButtonChangeColorBackgroundSetting()
            if (idButton != 0) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonChangeBackground(button_change_background, btnColorF)
            }
        }
    private val idButtonChangeScreenTimeOutClick: Unit
        private get() {
            val idButton: Int = sharedPreferencesUtils!!.getButtonChangeScreenTimeOut()
            if (idButton != 0) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonChangeScreenTimeOut(button_change_screen_time_out, btnColorF)
            }
        }
    private val idButtonChangeTextSize: Unit
        private get() {
            val idButton: Int = sharedPreferencesUtils!!.getButtonChangeTextSize()
            if (idButton != 0) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonTextSize(button_change_text_size, btnColorF)
            }
        }
    private val idButtonChangeFontStyle: Unit
        private get() {
            val idButton: Int =sharedPreferencesUtils!!.getButtonChangeFontStyle()
            if (idButton != 0) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonFontStyle(button_change_font_style, btnColorF)
            }
        }
    private val idButtonChangeLineHeight: Unit
        private get() {
            val idButton: Int = sharedPreferencesUtils!!.getButtonChangeLineHeight()
            if (idButton != 60) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonLineHeight(button_change_line_height, btnColorF)
            }
        }
    private val idButtonChangeReadStyle: Unit
        private get() {
            val idButton: Int =sharedPreferencesUtils!!.getButtonChangeReadStyle()
            if (idButton != 0) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonReadStyle(button_change_read_style, btnColorF)
            }
        }
    private val idButtonChangeAutoScroll: Unit
        private get() {
            val idButton: Int = sharedPreferencesUtils!!.getButtonChangeAutoScroll()
            if (idButton != 0) {
                val btnColorF = findViewById<View>(idButton) as Button
                setFocusToGroupButtonAutoScroll(button_change_auto_scroll, btnColorF)
            }
        }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btntrang -> {
                sharedPreferencesUtils!!.setColorBackgroundReadStory(resources.getColor(R.color.colorWhite))
                sharedPreferencesUtils!!.setChangeTextColor(resources.getColor(R.color.colorBlack))
                setFocusToGroupButtonChangeBackground(button_change_background, btn_position_change_background[0])
            }
            R.id.btnden -> {
                sharedPreferencesUtils!!.setColorBackgroundReadStory(resources.getColor(R.color.colorBlack))
                setFocusToGroupButtonChangeBackground(button_change_background, btn_position_change_background[1])
                sharedPreferencesUtils!!.setChangeTextColor(resources.getColor(R.color.colorWhite))
            }
            R.id.btnvang -> {
                sharedPreferencesUtils!!.setColorBackgroundReadStory(resources.getColor(R.color.colorYellowReadStory))
                sharedPreferencesUtils!!.setChangeTextColor(resources.getColor(R.color.colorBlack))
                setFocusToGroupButtonChangeBackground(button_change_background, btn_position_change_background[2])
            }
            R.id.btnmanghinhsang30s -> {
                sharedPreferencesUtils!!.setScreenTimeOutReadStory(TextSize.THIRTY_SECOUND)
                setFocusToGroupButtonChangeScreenTimeOut(button_change_screen_time_out, btn_position_change_screen_time_out[0])
            }
            R.id.btnmanghinhsang1p -> {
                sharedPreferencesUtils!!.setScreenTimeOutReadStory(TextSize.ONE_MINUTE)
                setFocusToGroupButtonChangeScreenTimeOut(button_change_screen_time_out, btn_position_change_screen_time_out[1])
            }
            R.id.btnmanghinhsang10phut -> {
                sharedPreferencesUtils!!.setScreenTimeOutReadStory(TextSize.TEN_MINUTE)
                setFocusToGroupButtonChangeScreenTimeOut(button_change_screen_time_out, btn_position_change_screen_time_out[2])
            }
            R.id.btnmanghinhsang30phut -> {
                sharedPreferencesUtils!!.setScreenTimeOutReadStory(TextSize.THIRTY_MINUTE)
                setFocusToGroupButtonChangeScreenTimeOut(button_change_screen_time_out, btn_position_change_screen_time_out[3])
            }
            R.id.btnSize12 -> {
                sharedPreferencesUtils!!.setTextSizeReadStory(TextSize.size_12)
                setFocusToGroupButtonTextSize(button_change_text_size, btn_position_change_text_size[0])
            }
            R.id.btnSize14 -> {
                sharedPreferencesUtils!!.setTextSizeReadStory(TextSize.size_14)
                setFocusToGroupButtonTextSize(button_change_text_size, btn_position_change_text_size[1])
            }
            R.id.btnSize16 -> {
                sharedPreferencesUtils!!.setTextSizeReadStory(TextSize.size_16)
                setFocusToGroupButtonTextSize(button_change_text_size, btn_position_change_text_size[2])
            }
            R.id.btnSize18 -> {
                sharedPreferencesUtils!!.setTextSizeReadStory(TextSize.size_18)
                setFocusToGroupButtonTextSize(button_change_text_size, btn_position_change_text_size[3])
            }
            R.id.btnSize20 -> {
                sharedPreferencesUtils!!.setTextSizeReadStory(TextSize.size_20)
                setFocusToGroupButtonTextSize(button_change_text_size, btn_position_change_text_size[4])
            }
            R.id.btnSize22 -> {
                sharedPreferencesUtils!!.setTextSizeReadStory(TextSize.size_22)
                setFocusToGroupButtonTextSize(button_change_text_size, btn_position_change_text_size[5])
            }
            R.id.btnFontimesnewroman -> {
                sharedPreferencesUtils!!.setFontReadStory(R.font.timesnewroman)
                setFocusToGroupButtonFontStyle(button_change_font_style, btn_position_change_font_style[0])
            }
            R.id.btnFontserif -> {
                sharedPreferencesUtils!!.setFontReadStory(R.font.serif)
                setFocusToGroupButtonFontStyle(button_change_font_style, btn_position_change_font_style[1])
            }
            R.id.btnFontbookerly -> {
                sharedPreferencesUtils!!.setFontReadStory(R.font.bookerly_regular)
                setFocusToGroupButtonFontStyle(button_change_font_style, btn_position_change_font_style[2])
            }
            R.id.btnFonthelvetica -> {
                sharedPreferencesUtils!!.setFontReadStory(R.font.helvetica_world_regular)
                setFocusToGroupButtonFontStyle(button_change_font_style, btn_position_change_font_style[3])
            }
            R.id.btnFontlora -> {
                sharedPreferencesUtils!!.setFontReadStory(R.font.lora_regular)
                setFocusToGroupButtonFontStyle(button_change_font_style, btn_position_change_font_style[4])
            }
            R.id.btncuondoc -> setFocusToGroupButtonReadStyle(button_change_read_style, btn_position_change_read_style[0])
            R.id.btnlattrang -> setFocusToGroupButtonReadStyle(button_change_read_style, btn_position_change_read_style[1])
            R.id.btnkkethop -> setFocusToGroupButtonReadStyle(button_change_read_style, btn_position_change_read_style[2])
            R.id.btnlineHeight_1 -> {
                setFocusToGroupButtonLineHeight(button_change_line_height, btn_position_change_line_height[0])
                sharedPreferencesUtils!!.setLineHeight(TextSize.line_Height_1)
            }
            R.id.btnlineHeight_2 -> {
                setFocusToGroupButtonLineHeight(button_change_line_height, btn_position_change_line_height[1])
                sharedPreferencesUtils!!.setLineHeight(TextSize.line_Height_2)
            }
            R.id.btnlineHeight_3 -> {
                setFocusToGroupButtonLineHeight(button_change_line_height, btn_position_change_line_height[2])
                sharedPreferencesUtils!!.setLineHeight(TextSize.line_Height_3)
            }
            R.id.btnlineHeight_4 -> {
                setFocusToGroupButtonLineHeight(button_change_line_height, btn_position_change_line_height[3])
                sharedPreferencesUtils!!.setLineHeight(TextSize.line_Height_4)
            }
            R.id.btnlineHeight_5 -> {
                setFocusToGroupButtonLineHeight(button_change_line_height, btn_position_change_line_height[4])
                sharedPreferencesUtils!!.setLineHeight(TextSize.line_Height_5)
            }
            R.id.btnlineHeight_6 -> {
                setFocusToGroupButtonLineHeight(button_change_line_height, btn_position_change_line_height[5])
                sharedPreferencesUtils!!.setLineHeight(TextSize.line_Height_6)
            }
            R.id.btnautoscroll_No -> setFocusToGroupButtonAutoScroll(button_change_auto_scroll, btn_position_change_auto_scroll[0])
            R.id.btnautoscroll_Yes -> setFocusToGroupButtonAutoScroll(button_change_auto_scroll, btn_position_change_auto_scroll[1])
            R.id.seekBarSettingAutoScroll -> eventSeeBarSetting()
        }
    }

    private fun eventSeeBarSetting() {
        val seekBar = findViewById<SeekBar>(R.id.seekBarSettingAutoScroll)
        seekBar.progress =  sharedPreferencesUtils!!.getSeeBar()
        //        seekBar.incrementProgressBy(1);
        seekBar.max = 5
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.min = 1
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var seekBarProgress = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                seekBarProgress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                sharedPreferencesUtils!!.setSeeBar(seekBarProgress)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        idButtonChangeBackgroundClick
        idButtonChangeScreenTimeOutClick
        idButtonChangeTextSize
        idButtonChangeFontStyle
        idButtonChangeLineHeight
    }

    private fun setFocusToGroupButtonChangeBackground(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeColorBackgroundSetting(btn_focus!!.id)
        button_change_background = btn_focus
    }

    private fun setFocusToGroupButtonChangeScreenTimeOut(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeScreenTimeOut(btn_focus!!.id)
        button_change_screen_time_out = btn_focus
    }

    private fun setFocusToGroupButtonTextSize(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeTextSize(btn_focus!!.id)
        button_change_text_size = btn_focus
    }

    private fun setFocusToGroupButtonFontStyle(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeFontStyle(btn_focus!!.id)
        button_change_font_style = btn_focus
    }

    private fun setFocusToGroupButtonReadStyle(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeReadStyle(btn_focus!!.id)
        button_change_read_style = btn_focus
    }

    private fun setFocusToGroupButtonLineHeight(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeLineHeight(btn_focus!!.id)
        button_change_line_height = btn_focus
    }

    private fun setFocusToGroupButtonAutoScroll(btn_unfocus: Button?, btn_focus: Button?) {
        setFocus(btn_unfocus, btn_focus)
        sharedPreferencesUtils!!.setButtonChangeAutoScroll(btn_focus!!.id)
        button_change_auto_scroll = btn_focus
    }

    private fun setFocus(btn_unfocus: Button?, btn_focus: Button?) {
        btn_unfocus!!.setBackgroundResource(R.drawable.btn_unfocus)
        btn_unfocus.setTextColor(resources.getColor(R.color.colorBlack))
        btn_focus!!.setBackgroundResource(R.drawable.btn_focus)
        btn_focus.setTextColor(resources.getColor(R.color.colorWhite))
    }

    private fun init() {
        nestedScrollView = findViewById(R.id.nestedScrollViewSetting)
        findViewById<View>(R.id.btntrang).setOnClickListener(this)
        findViewById<View>(R.id.btnden).setOnClickListener(this)
        findViewById<View>(R.id.btnvang).setOnClickListener(this)
        findViewById<View>(R.id.btnSize12).setOnClickListener(this)
        findViewById<View>(R.id.btnSize14).setOnClickListener(this)
        findViewById<View>(R.id.btnSize16).setOnClickListener(this)
        findViewById<View>(R.id.btnSize18).setOnClickListener(this)
        findViewById<View>(R.id.btnSize20).setOnClickListener(this)
        findViewById<View>(R.id.btnSize22).setOnClickListener(this)
        findViewById<View>(R.id.btnFontimesnewroman).setOnClickListener(this)
        findViewById<View>(R.id.btnFontserif).setOnClickListener(this)
        findViewById<View>(R.id.btnFontbookerly).setOnClickListener(this)
        findViewById<View>(R.id.btnFonthelvetica).setOnClickListener(this)
        findViewById<View>(R.id.btnFontlora).setOnClickListener(this)
        findViewById<View>(R.id.btnmanghinhsang30s).setOnClickListener(this)
        findViewById<View>(R.id.btnmanghinhsang1p).setOnClickListener(this)
        findViewById<View>(R.id.btnmanghinhsang10phut).setOnClickListener(this)
        findViewById<View>(R.id.btnmanghinhsang30phut).setOnClickListener(this)
        findViewById<View>(R.id.btnautoscroll_Yes).setOnClickListener(this)
        findViewById<View>(R.id.btnautoscroll_No).setOnClickListener(this)
        findViewById<View>(R.id.seekBarSettingAutoScroll).setOnClickListener(this)
        //        findViewById(R.id.btnAutoChangePage10).setOnClickListener(this);
//        findViewById(R.id.btnAutoChangePage20).setOnClickListener(this);
//        findViewById(R.id.btnAutoChangePage30).setOnClickListener(this);
//        findViewById(R.id.btnAutoChangePage40).setOnClickListener(this);
//        findViewById(R.id.btnAutoChangePage50).setOnClickListener(this);
//        findViewById(R.id.btnAutoChangePage60).setOnClickListener(this);
    }

    private fun actionToolBar() {
        toolbar_Setting = findViewById(R.id.toolbar_Setting)
        setSupportActionBar(toolbar_Setting)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar_Setting!!.setNavigationOnClickListener(View.OnClickListener { //                Intent intent = new Intent(SettingActivity.this,ReadStoryActivity.class);
//                startActivity(intent);
            finish()
        })
    }
}