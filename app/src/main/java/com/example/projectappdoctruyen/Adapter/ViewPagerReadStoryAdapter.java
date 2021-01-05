package com.example.projectappdoctruyen.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectappdoctruyen.Database.DatabaseHelper;
import com.example.projectappdoctruyen.Fragment.ReadFragment;
import com.example.projectappdoctruyen.Model.Chapter;

import java.util.List;

public class ViewPagerReadStoryAdapter extends FragmentStatePagerAdapter {
    List<Chapter> chapterArrayList;

    public ViewPagerReadStoryAdapter(@NonNull FragmentManager fm,int behavior, List<Chapter> chapterArrayList) {
        super(fm,behavior);
        this.chapterArrayList = chapterArrayList;

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (chapterArrayList == null || chapterArrayList.isEmpty()){
            return null;
        }
        Chapter chapter = chapterArrayList.get(position);
        ReadFragment readFragment = new ReadFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("KEY",chapter);
        readFragment.setArguments(bundle);
        return readFragment;
    }

    @Override
    public int getCount() {
        if (chapterArrayList!=null) {
            return chapterArrayList.size();
        }
        return 0;
//        return chapterArrayList.size();

    }
}
