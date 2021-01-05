package com.example.projectappdoctruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectappdoctruyen.Adapter.ViewPagerReadStoryAdapter;
import com.example.projectappdoctruyen.Database.DatabaseHelper;
import com.example.projectappdoctruyen.Model.Chapter;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class ReadStoryActivity extends AppCompatActivity {
    public  Toolbar testToolBarId;
    public  LinearLayout linearLayout_Visible;
    private Chapter chapter;
    private AdView adView;
    private SharedPreferences_Utils sharedPreferencesUtils;
    private ViewPager viewPager;
    private ViewPagerReadStoryAdapter viewPagerReadStoryAdapter;
    private List<Chapter> chapterArrayList;
    private DatabaseHelper databaseHelper;
    private Story story;
        private ArrayList<Story> storyArrayList;
    private TextView txtBack ,txtCurrentNumber, txtTotalNumber, txtNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read_story);
        sharedPreferencesUtils = new SharedPreferences_Utils(this);
        databaseHelper = new DatabaseHelper(this);
        init();
        actionToolBar();
        dataIntent();
        chapterArrayList =  databaseHelper.getChapTer(String.valueOf(chapter.getIdStory()));
        int idStory = chapter.getIdStory();
        story = databaseHelper.getStoryById(idStory+"");
        setAdapterViewPager();
        int  index = indexChapter(chapterArrayList,chapter.getIdChapter());
        txtCurrentNumber.setText(index+ 1 +"");
        txtTotalNumber.setText(String.valueOf(chapterArrayList.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                txtCurrentNumber.setText(String.valueOf(position + 1));
                if (position ==0){
                    txtBack.setVisibility(View.GONE);
                    txtNext.setVisibility(View.VISIBLE);
                }else if(position == chapterArrayList.size() -1 ){
                    txtBack.setVisibility(View.VISIBLE);
                    txtNext.setVisibility(View.GONE);
                }else {
                    txtNext.setVisibility(View.VISIBLE);
                    txtBack.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() -1);
            }
        });
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
    }
    private  int indexChapter(List<Chapter> list, int id){
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getIdChapter() == id){
                return i;
            }
        }return -1;
    }
    private void setAdapterViewPager() {
        viewPagerReadStoryAdapter = new ViewPagerReadStoryAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,chapterArrayList);
        viewPager.setAdapter(viewPagerReadStoryAdapter);
        int indexofchapter  = -1;
        for (int i = 0;i<chapterArrayList.size();i++){
            if (chapterArrayList.get(i).getIdChapter() == chapter.getIdChapter()){
                indexofchapter = i;
            }
        }
        viewPager.setCurrentItem(indexofchapter);
    }
    private void dataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            chapter = (Chapter) intent.getSerializableExtra("Chapter");
            Log.d("FFASDAS",chapter.getTitle()+"");
        }
    }
    private void actionToolBar() {
        testToolBarId = findViewById(R.id.testToolBarId);
        setSupportActionBar(testToolBarId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        testToolBarId.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void init() {
        txtBack = findViewById(R.id.txtBack);
        txtCurrentNumber = findViewById(R.id.txtCurrentNumber);
        txtTotalNumber = findViewById(R.id.txtTotalNumber);
        txtNext = findViewById(R.id.txtNext);
        adView = findViewById(R.id.adView);
        viewPager = findViewById(R.id.fr_container_test);
        linearLayout_Visible = findViewById(R.id.linearLayout_Visible);


    }
}