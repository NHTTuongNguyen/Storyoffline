package com.example.projectappdoctruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.projectappdoctruyen.Adapter.ChapterAdapter;
import com.example.projectappdoctruyen.Model.Chapter;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ChapterListsActivity extends AppCompatActivity {
    private Story story;
    private RecyclerView recyclerViewChapTer;
    private LinearLayoutManager linearLayoutManager;
    private ChapterAdapter chapterAdapter;
    private List<Chapter> chapterList;
    private DatabaseHelper mDBHelper;
    private Toolbar toolbar_ChapterStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapterlist);
        getDataIntentFromStory();
        actionToolBar();
        buildRecyclerViewChapList();

    }

    private void buildRecyclerViewChapList() {
        recyclerViewChapTer = findViewById(R.id.recyclerViewChapTer);
        mDBHelper = new DatabaseHelper(ChapterListsActivity.this);
        int idChapterList = story.getId();
        chapterList = mDBHelper.getChapTer(String.valueOf(idChapterList));
        chapterAdapter = new ChapterAdapter(this, (ArrayList<Chapter>) chapterList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewChapTer.setLayoutManager(linearLayoutManager);
        recyclerViewChapTer.setHasFixedSize(true);
        recyclerViewChapTer.setAdapter(chapterAdapter);
        chapterAdapter.notifyDataSetChanged();
    }

    private void actionToolBar() {
        toolbar_ChapterStory = findViewById(R.id.toolbar_ChapterStory);
        setSupportActionBar(toolbar_ChapterStory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_ChapterStory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getDataIntentFromStory() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("ChapterId")) {
                story = (Story) intent.getSerializableExtra("ChapterId");
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ChapterListsActivity.this,SettingActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}