package com.example.projectappdoctruyen.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.Model.Story
import java.util.*

class DatabaseHelper(private val mContext: Context?) : SQLiteOpenHelper(mContext, DBNAME, null, 1) {
    private var count = 0
    private var mDatabase: SQLiteDatabase? = null
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {}
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}
    fun openDatabase() {
        val dbPath = mContext!!.getDatabasePath(DBNAME).path
        if (mDatabase != null && mDatabase!!.isOpen) {
            return
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    fun closeDatabase() {
        if (mDatabase != null) {
            mDatabase!!.close()
        }
    }

    val story: MutableList<Story>
        get() {
            val storyList: MutableList<Story> = ArrayList()
            val db = this.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM $UDV_CATEGORY ", null)
            if (cursor.moveToFirst()) {
                do {
                    val story = Story()
                    story.id = cursor.getInt(cursor.getColumnIndex(catId))
                    story.name = cursor.getString(cursor.getColumnIndex(catName))
                    story.conTent = cursor.getString(cursor.getColumnIndex(kdCatName))
                    storyList.add(story)
                } while (cursor.moveToNext())
            }
            db.close()
            return storyList
        }

    fun getChapTer(idStory: String): List<Chapter> {
        val list: MutableList<Chapter> = ArrayList()
        openDatabase()
        val cursor = mDatabase!!.rawQuery("SELECT * FROM udv_story WHERE catId = ? ORDER BY id", arrayOf(idStory))
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val chapter = Chapter(cursor.getInt(cursor.getColumnIndex(ID_CHAPTER)),
                    cursor.getInt(cursor.getColumnIndex(catId)),
                    cursor.getString(cursor.getColumnIndex(TITLE_CHAPTER)),
                    cursor.getString(cursor.getColumnIndex(DETAIL_CHAPTER)))
            list.add(chapter)
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()
        return list
    }

    fun getChapTerLimit5(idStory: String): List<Chapter> {
        val list: MutableList<Chapter> = ArrayList()
        openDatabase()
        val cursor = mDatabase!!.rawQuery("SELECT * FROM udv_story WHERE catId = ? ORDER BY ROWID ASC LIMIT 5", arrayOf(idStory))
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val chapter = Chapter(cursor.getInt(cursor.getColumnIndex(ID_CHAPTER)),
                    cursor.getInt(cursor.getColumnIndex(catId)),
                    cursor.getString(cursor.getColumnIndex(TITLE_CHAPTER)),
                    cursor.getString(cursor.getColumnIndex(DETAIL_CHAPTER)))
            list.add(chapter)
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()
        return list
    }

    fun getStoryById(idStory: String): Story {
        val story: Story
        openDatabase()
        val cursor = mDatabase!!.rawQuery("SELECT * FROM udv_category WHERE catId = ?", arrayOf(idStory))
        cursor.moveToFirst()
        story = Story(cursor.getInt(cursor.getColumnIndex(catId)),
                cursor.getString(cursor.getColumnIndex(catName)),
                cursor.getString(cursor.getColumnIndex(kdCatName)))
        cursor.moveToNext()
        cursor.close()
        closeDatabase()
        return story
    }

    fun getChapterDefaultLimit1(idStory: String): Chapter {
        var chapter = Chapter()
        openDatabase()
        val cursor = mDatabase!!.rawQuery("SELECT * FROM udv_story WHERE catId = ? ORDER BY ROWID ASC LIMIT 1", arrayOf(idStory))
        cursor.moveToFirst()
        chapter = Chapter(cursor.getInt(cursor.getColumnIndex(ID_CHAPTER)),
                cursor.getInt(cursor.getColumnIndex(catId)),
                cursor.getString(cursor.getColumnIndex(TITLE_CHAPTER)),
                cursor.getString(cursor.getColumnIndex(DETAIL_CHAPTER)))
        cursor.moveToNext()
        cursor.close()
        closeDatabase()
        Log.d("getColumnIndex_Chapter", cursor.toString() + "")
        return chapter
    }

    fun getTaskCount(tasklist_id: Int): Int {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT COUNT (*) FROM $UDV_STORY WHERE $catId=?", arrayOf(tasklist_id.toString()))
        if (cursor.moveToFirst()) {
            do {
                count = cursor.getInt(0)
            } while (cursor.moveToNext())
        }
        db.close()
        return count
    }

    fun search(keyword: String): List<Story?>? {
        var stories: MutableList<Story?>? = null
        try {
            val sqLiteDatabase = readableDatabase
            val cursor = sqLiteDatabase.rawQuery("select * from $UDV_CATEGORY where $catName like ?", arrayOf("%$keyword%"))
            if (cursor.moveToFirst()) {
                stories = ArrayList()
                do {
                    val story = Story()
                    story.id = cursor.getInt(cursor.getColumnIndex(catId))
                    story.name = cursor.getString(cursor.getColumnIndex(catName))
                    story.conTent = cursor.getString(cursor.getColumnIndex(kdCatName))
                    stories.add(story)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            stories = null
        }
        return stories
    }

    companion object {
        const val DBNAME = "Full.sqlite"
        const val DBLOCATION = "/data/data/com.example.projectappdoctruyen/databases/"
        private const val UDV_STORY = "udv_story"
        private const val UDV_CATEGORY = "udv_category"
        private const val catId = "catId"
        private const val catName = "catName"
        private const val kdCatName = "kdCatName"
        private const val ID_CHAPTER = "id"
        private const val TITLE_CHAPTER = "title"
        private const val DETAIL_CHAPTER = "detail"
    }


}