package com.example.projectappdoctruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectappdoctruyen.Adapter.ChapterWatchedAdapter;
import com.example.projectappdoctruyen.Model.Chapter;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Database.DatabaseHelper;
import com.example.projectappdoctruyen.Service.CheckConnection;
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils;
import com.example.projectappdoctruyen.Utils.KeyIntent;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoryDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbarStoryDetail;
    private DatabaseHelper mDBHelper;
    private Chapter chapter;
    private TextView textViewTitle,txtChapterStoryDetail,txtContentStory,txtNoReadStory;
    private Story story;
    private ArrayList<Chapter>chapterListLimit5;
    private ChapterWatchedAdapter chapterWatchedAdapter;
    private RecyclerView recyclerView_ChapterWatched;
    private LinearLayoutManager linearLayoutManager;
    private ImageView imgStoryDetail;
    private ArrayList<Chapter> chapterList;
    private SharedPreferences_Utils sharedPreferences_utils;
    private TemplateView templateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        initView();
        getDataIntentFromStoryId();
        actionToolBar();
        initDataBase();
        setTextViewAndImageView();
        checkConnectAdsView();

    }

    private void initDataBase() {
        mDBHelper = new DatabaseHelper(StoryDetailActivity.this);
        chapter = mDBHelper.getChapterDefaultLimit1(story.getId()+"");
    }


    private void checkConnectAdsView() {
        if (CheckConnection.isConnectedtoInternet(getApplicationContext())){
            setAdsView();
        }else {
            templateView.setVisibility(View.GONE);
        }
    }

    private void setAdsView() {
        MobileAds.initialize(StoryDetailActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdLoader.Builder builder = new AdLoader.Builder(StoryDetailActivity.this,getString(R.string.Ads_appId));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                templateView.setNativeAd(unifiedNativeAd);
            }
        });
        AdLoader adLoader  = builder.build();
        AdRequest adRequest = new AdRequest.Builder().build();
        adLoader.loadAd(adRequest);
    }
    private void initView() {
        sharedPreferences_utils = new SharedPreferences_Utils(this);
        templateView = findViewById(R.id.template_StoryDetailActivity);
        txtNoReadStory = findViewById(R.id.txtNoReadStory);
        txtContentStory= findViewById(R.id.txtContentStory);
        imgStoryDetail = findViewById(R.id.imgStoryDetail);
        recyclerView_ChapterWatched = findViewById(R.id.recyclerView_ChapterWatched);
        textViewTitle = findViewById(R.id.textViewTitle);
        txtChapterStoryDetail = findViewById(R.id.txtChapterStoryDetail);
        findViewById(R.id.btnListChapters).setOnClickListener(this);
        findViewById(R.id.btnFeedBackChapter).setOnClickListener(this);
        findViewById(R.id.btnChapterRedStoryLimit1).setOnClickListener(this);
    }

    private void setTextViewAndImageView() {
        textViewTitle.setText(story.getName());
        txtContentStory.setText(story.getConTent());
        int kq = story.getId() %16;
        int id = this.getResources().getIdentifier(
                "com.example.projectappdoctruyen:drawable/s" + kq, null, null);
        imgStoryDetail.setImageResource(id);
        mDBHelper = new DatabaseHelper(this);
        int setCountStory =  mDBHelper.getTaskCount(story.getId());
        txtChapterStoryDetail.setText(setCountStory+" Chương");
    }

    private void getDataIntentFromStoryId() {
        Intent intent  = getIntent();
        if (intent != null)
            if (intent.hasExtra(KeyIntent.KEY_STORY)){
               story  = (Story) getIntent().getSerializableExtra(KeyIntent.KEY_STORY);
            }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnListChapters:
                Intent intent = new Intent(StoryDetailActivity.this, ChapterListsActivity.class);
                intent.putExtra("ChapterId", story);
                startActivity(intent);
                break;
            case R.id.btnFeedBackChapter:
                Intent intentACTION_VIEW = new Intent(Intent.ACTION_VIEW);
                intentACTION_VIEW.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.zing.mp3&hl=vi"));
                startActivity(intentACTION_VIEW);
                break;
            case R.id.btnChapterRedStoryLimit1:
                Intent intentRedStoryLimit1 = new Intent(StoryDetailActivity.this,ReadStoryActivity.class);
                intentRedStoryLimit1.putExtra("Chapter",chapter);
                startActivity(intentRedStoryLimit1);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Chapter> chapterListFromSharePre =
                sharedPreferences_utils.getDataSharePreferences_From_ChapterWatched(story);
        if (chapterListFromSharePre!=null) {
            chapterWatchedAdapter = new ChapterWatchedAdapter(this, chapterListFromSharePre);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView_ChapterWatched.setLayoutManager(linearLayoutManager);
            recyclerView_ChapterWatched.setHasFixedSize(true);
            recyclerView_ChapterWatched.setAdapter(chapterWatchedAdapter);
            chapterWatchedAdapter.notifyDataSetChanged();

            for (int i  = 0;i<chapterListFromSharePre.size();i++){
                Log.d("FFFFF",chapterListFromSharePre.get(i).getIdChapter()+"");
            }
        }
    }

    private void actionToolBar() {
        toolbarStoryDetail = findViewById(R.id.toolbar_Truyen);
        setSupportActionBar(toolbarStoryDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarStoryDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(StoryDetailActivity.this,SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}