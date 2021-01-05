package com.example.projectappdoctruyen.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.Model.Chapter;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Full.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.projectappdoctruyen/databases/";
    private static final String UDV_STORY = "udv_story";
    private static final String UDV_CATEGORY = "udv_category";
    private static final String catId = "catId";
    private static final String catName = "catName";
    private static final String kdCatName = "kdCatName";
    private static final String ID_CHAPTER = "id";
    private static final String TITLE_CHAPTER = "title";
    private static final String DETAIL_CHAPTER = "detail";


    private Context mContext;
    private int count;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
        this.mDatabase = mDatabase;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }
    public List<Story> getStory() {
        List<Story> storyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+UDV_CATEGORY+" ",null);
        if (cursor.moveToFirst()){
            do {
                Story story = new Story();
                story.setId(cursor.getInt(cursor.getColumnIndex(catId)));
                story.setName(cursor.getString(cursor.getColumnIndex(catName)));
                story.setConTent(cursor.getString(cursor.getColumnIndex(kdCatName)));
                storyList.add(story);

            }while (cursor.moveToNext());
        }
        db.close();
        return storyList;
    }
    public List<Chapter> getChapTer(String idStory) {
        List<Chapter> list = new ArrayList<Chapter>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM udv_story WHERE catId = ? ORDER BY id" , new String[] {idStory});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

           Chapter chapter  = new Chapter(cursor.getInt( cursor.getColumnIndex(ID_CHAPTER)),
                    cursor.getInt( cursor.getColumnIndex(catId)),
                    cursor.getString( cursor.getColumnIndex(TITLE_CHAPTER)),
                    cursor.getString( cursor.getColumnIndex(DETAIL_CHAPTER)));
            list.add(chapter);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return list;
    }
    public List<Chapter> getChapTerLimit5(String idStory) {
        List<Chapter> list = new ArrayList<Chapter>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM udv_story WHERE catId = ? ORDER BY ROWID ASC LIMIT 5", new String[] {idStory});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Chapter chapter  = new Chapter(cursor.getInt( cursor.getColumnIndex(ID_CHAPTER)),
                    cursor.getInt( cursor.getColumnIndex(catId)),
                    cursor.getString( cursor.getColumnIndex(TITLE_CHAPTER)),
                    cursor.getString( cursor.getColumnIndex(DETAIL_CHAPTER)));
            list.add(chapter);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return list;
    }
    public Story getStoryById (String idStory){
            Story story ;
            openDatabase();
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM udv_category WHERE catId = ?", new String[] {idStory});
            cursor.moveToFirst();
            story = new Story (cursor.getInt(cursor.getColumnIndex(catId)),
                    cursor.getString(cursor.getColumnIndex(catName)),
                    cursor.getString(cursor.getColumnIndex(kdCatName)));
            cursor.moveToNext();
            cursor.close();
            closeDatabase();
            return story;
    }
    public Chapter getChapterDefaultLimit1(String idStory){
        Chapter chapter = new Chapter();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM udv_story WHERE catId = ? ORDER BY ROWID ASC LIMIT 1", new String[] {idStory});
        cursor.moveToFirst();
        chapter  = new Chapter(cursor.getInt( cursor.getColumnIndex(ID_CHAPTER)),
                cursor.getInt( cursor.getColumnIndex(catId)),
                cursor.getString( cursor.getColumnIndex(TITLE_CHAPTER)),
                cursor.getString( cursor.getColumnIndex(DETAIL_CHAPTER)));
        cursor.moveToNext();
        cursor.close();
        closeDatabase();
        Log.d("getColumnIndex_Chapter",cursor+"");
        return chapter;
    }
    public int getTaskCount(int tasklist_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT COUNT (*) FROM " + UDV_STORY + " WHERE " + catId + "=?", new String[] { String.valueOf(tasklist_id) });
        if (cursor.moveToFirst()){
            do {
                count = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        db.close();
        return count;
    }


    public List<Story> search(String keyword) {
        List<Story> stories = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + UDV_CATEGORY + " where " + catName + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                stories = new ArrayList<Story>();
                do {
                    Story story = new Story();
                    story.setId(cursor.getInt(cursor.getColumnIndex(catId)));
                    story.setName(cursor.getString(cursor.getColumnIndex(catName)));
                    story.setConTent(cursor.getString(cursor.getColumnIndex(kdCatName)));
                    stories.add(story);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            stories = null;
        }
        return stories;
    }
}



