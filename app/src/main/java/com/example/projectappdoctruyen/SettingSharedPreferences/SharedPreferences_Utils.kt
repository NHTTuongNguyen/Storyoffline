package com.example.projectappdoctruyen.SettingSharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.graphics.fonts.Font
import android.util.Log
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Utils.TextSize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

//package com.example.projectappdoctruyen.ChangeColor;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Color;
//import android.view.View;
//import android.view.WindowManager;
//
//import com.example.projectappdoctruyen.Activity.SettingActivity;
//
class SharedPreferences_Utils(var context: Context) {
     val MyPREFERENCES = "MyPrefsSetting1111"
     val Color = "color_Key"
     val ChangeTextColor = "change_text_color_Key"
     val SaveChapterWatched = "save_chapterWatched_key"
     val SaveStoryWatched = "save_storyWatched_key"
     val Size = "size_Key"
     val Font = "font_Key"
     val Default = 0
     val ButtonChangeBackgroundColor = "button_color_Key"
     val ButtonChangeScreenTimeOut = "button_screen_time_out_Key"
     val ButtonChangeTextSize = "button_text_size_Key"
     val ButtonChangeFontStyle = "button_font_style_Key"
     val ButtonChangeReadStyle = "button_read_style_Key"
     val ButtonChangeLineHeight = "button_line_height_Key"
     val ButtonChangeAutoScroll = "button_auto_Scroll_Key"
     val SaveButtonStory = "save_button_story_key"
     val LineHeight = "line_height_key"
     val ScreenTimeOut = "screentimeout_Key"
     val SeeBar = "see_bar_Key"
     val Default_SeekBar = 1

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)

    fun setSeeBar(seeBar: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(SeeBar, seeBar)
        editor.commit()
    }

    fun getSeeBar(): Int {
        return sharedPreferences.getInt(SeeBar, Default_SeekBar)
    }

    fun setChangeTextColor(changeTextColor: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(ChangeTextColor, changeTextColor)
        editor.commit()
    }

    fun getChangeTextColor(): Int {
        return sharedPreferences.getInt(ChangeTextColor, android.graphics.Color.BLACK)
    }

    fun setColorBackgroundReadStory (color: Int){
        val editor = sharedPreferences.edit()
        editor.putInt(Color, color)
        editor.commit()
    }
    fun getColorBackgroundReadStory() : Int{
        return sharedPreferences.getInt(Color, context.resources.getColor(R.color.colorWhite))
    }



    fun setTextSizeReadStory(size: Float){
        val editor = sharedPreferences.edit()
        editor.putFloat(Size, size)
        editor.commit()
    }
    fun getTextSizeReadStory() :Float{
        return sharedPreferences.getFloat(Size, TextSize.size_default)
    }


    fun setFontReadStory( font: Int){
        val editor = sharedPreferences.edit()
        editor.putInt(Font, font)
        editor.commit()
    }

    fun getFontReadStory():Int{
        return sharedPreferences.getInt(Font, R.font.arial)
    }



    fun setScreenTimeOutReadStory(timeOut: Int){
        val editor = sharedPreferences.edit()
        editor.putInt(ScreenTimeOut, timeOut)
        editor.commit()
    }

    fun getScreenTimeOutReadStory() :Int{
        return sharedPreferences.getInt(ScreenTimeOut, TextSize.DEFAULT_SECOUND)
    }



    fun setLineHeight(lineHeight: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(LineHeight, lineHeight)
        editor.commit()
    }

    fun getLineHeight(): Float {
        return sharedPreferences.getFloat(LineHeight, 35f)
    }

    fun setButtonChangeLineHeight(buttonChangeLineHeight: Int) {

        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeLineHeight, buttonChangeLineHeight)
        editor.commit()
    }

    fun getButtonChangeLineHeight(): Int {
        return sharedPreferences.getInt(ButtonChangeLineHeight, 60)
    }

    fun setButtonChangeAutoScroll(buttonChangeAutoScroll: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeAutoScroll, buttonChangeAutoScroll)
        editor.commit()    }

    fun getButtonChangeAutoScroll(): Int {
        return sharedPreferences.getInt(ButtonChangeAutoScroll, Default)
    }

    fun setButtonChangeReadStyle(buttonReadStyle: Int) {


        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeReadStyle, buttonReadStyle)
        editor.commit()
    }

    fun getButtonChangeReadStyle(): Int {
        return sharedPreferences.getInt(ButtonChangeReadStyle, Default)
    }

    fun setButtonChangeFontStyle(buttonFontStyle: Int) {


        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeFontStyle, buttonFontStyle)
        editor.commit()
    }

    fun getButtonChangeFontStyle(): Int {
        return sharedPreferences.getInt(ButtonChangeFontStyle, Default)
    }

    fun setButtonChangeTextSize(buttonTextSize: Int) {


        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeTextSize, buttonTextSize)
        editor.commit()
    }

    fun getButtonChangeTextSize(): Int {
        return sharedPreferences.getInt(ButtonChangeTextSize, Default)
    }

    fun getButtonChangeColorBackgroundSetting(): Int{
      return  sharedPreferences.getInt(ButtonChangeBackgroundColor, Default)

    }


    fun setButtonChangeColorBackgroundSetting (buttonColor: Int){
        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeBackgroundColor, buttonColor)
        editor.commit()
    }
    fun setButtonChangeScreenTimeOut(buttonScreenTimeOut: Int) {

        val editor = sharedPreferences.edit()
        editor.putInt(ButtonChangeScreenTimeOut, buttonScreenTimeOut)
        editor.commit()

    }

    fun getButtonChangeScreenTimeOut(): Int {
        return sharedPreferences.getInt(ButtonChangeScreenTimeOut, Default)
    }
    fun setDataSharePreferences_From_Chapter(chapter: Chapter?) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonStoryWatched = sharedPreferences.getString(SaveChapterWatched, null)
        var chapterArrayList = ArrayList<Chapter>()
        if (jsonStoryWatched != null) {
            val type = object : TypeToken<ArrayList<Chapter?>?>() {}.type
            chapterArrayList = gson.fromJson(jsonStoryWatched, type)
        }
        val chapterSave = Chapter(
                chapter!!.idStory!!,
                chapter!!.idChapter!!,
                chapter!!.title)
        val indexChapter = findIndexChapter(chapterArrayList, chapter!!.idChapter!!)
        Log.d("21321321", indexChapter.toString() + "")
        if (indexChapter >= 0) {
            chapterArrayList.removeAt(indexChapter)
        }
        chapterArrayList.add(0, chapterSave)
        val json = gson.toJson(chapterArrayList)
        editor.putString(SaveChapterWatched, json)
        editor.apply()
    }

    fun setDataSharePreferences_From_StoryWatched(story: Story) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        var storyArrayListWatched = ArrayList<Story>()
        val jsonStoryWatched = sharedPreferences.getString(SaveStoryWatched, null)
        if (jsonStoryWatched != null) {
            val type = object : TypeToken<ArrayList<Story?>?>() {}.type
            storyArrayListWatched = gson.fromJson(jsonStoryWatched, type)
        }

        /////
        val index = findIndexStory(storyArrayListWatched, story.id!!)
        if (index > -1) {
            storyArrayListWatched.removeAt(index)
        }


//
        storyArrayListWatched.add(0, story)

//        if (!hasStoryInList(storyArrayListWatched,story.getId())) {
//            storyArrayListWatched.add(0, story);
//        }


        /////////
        if (storyArrayListWatched.size > 7) {
            storyArrayListWatched.removeAt(storyArrayListWatched.size - 1)
        }
        val json = gson.toJson(storyArrayListWatched)
        editor.putString(SaveStoryWatched, json)
        editor.apply()
    }

    private fun findIndexStory(storyArrayList: ArrayList<Story>, idStory: Int): Int {
        for (i in storyArrayList.indices) {
            if (storyArrayList[i].id == idStory) {
                return i
            }
        }
        return -1
    }

    private fun findIndexChapter(chapterArrayList: ArrayList<Chapter>, idChapter: Int): Int {
        for (i in chapterArrayList.indices) {
            if (chapterArrayList[i].idChapter == idChapter) {
                return i
            }
        }
        return -1
    }

    private fun hasStoryInList(storyArrayListWatched: ArrayList<Story>, idStory: Int): Boolean {
        for (i in storyArrayListWatched.indices) {
            if (storyArrayListWatched[i].id == idStory) {
                return true
            }
        }
        return false
    }

    private fun removeChapterInList(chapterArrayList: ArrayList<Chapter>, idChapter: Int): Boolean {
        for (i in chapterArrayList.indices) {
            if (chapterArrayList[i].idChapter == idChapter) {
                return true
            }
        }
        return false
    }

    fun get_SharedPreferences_Story_HistoryFragment(): ArrayList<Story?> {
        val gson = Gson()
        val json = sharedPreferences.getString(SaveStoryWatched, null)
        val type = object : TypeToken<ArrayList<Story?>?>() {}.type
        var storyArrayList = gson.fromJson<ArrayList<Story?>>(json, type)
        Log.d("get_tasksssslist", json.toString())
        if (storyArrayList == null) {
            storyArrayList = ArrayList()
        }
        return storyArrayList
    }

    fun getDataSharePreferences_From_ChapterWatched(storyss: Story?): ArrayList<Chapter?>? {
        val gson = Gson()
        val json = sharedPreferences.getString(SaveChapterWatched, null)
        val type = object : TypeToken<ArrayList<Chapter?>?>() {}.type //-> Array_list
        val list = gson.fromJson<ArrayList<Chapter>>(json, type)
        var chapterList: ArrayList<Chapter?>? = null
        if (list != null) {
            chapterList = chapterOfStory(list, storyss!!.id!!)
        }
        return chapterList
    }

    private fun chapterOfStory(chapterArrayList: ArrayList<Chapter>, idStory: Int): ArrayList<Chapter?> {
        val arrayList_Chapter: ArrayList<Chapter?> = ArrayList<Chapter?>()
        for (i in chapterArrayList.indices) {
            if (chapterArrayList[i].idStory == idStory) {
                arrayList_Chapter.add(chapterArrayList[i])
                if (arrayList_Chapter.size > 7) {
                    arrayList_Chapter.removeAt(arrayList_Chapter.size - 1)
                }
            }
        }
        return arrayList_Chapter
    }

    private fun hasChapterInList(chapterArrayListWatched: ArrayList<Chapter>, idChapter: Int): Boolean {
        for (i in chapterArrayListWatched.indices) {
            if (chapterArrayListWatched[i].idChapter == idChapter) {
                return true
            }
        }

        return false
    }




}