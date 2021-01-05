package com.example.projectappdoctruyen.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectappdoctruyen.Adapter.StoryAdapter;
import com.example.projectappdoctruyen.Database.DatabaseHelper;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StoryAdapter adapter;
    private List<Story> mStoryList;
    private DatabaseHelper mDBHelper;
    private SearchView searchView;
    private TextView txtNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        searchView = view.findViewById(R.id.search_barView);
        recyclerView = view.findViewById(R.id.recyclerView_Search);
        txtNoData = view.findViewById(R.id.txtNoData);
        mDBHelper = new DatabaseHelper(getActivity());

        mStoryList = mDBHelper.getStory();
        adapter = new StoryAdapter(getActivity(), (ArrayList<Story>) mStoryList);
//        adapter.setFilter(newList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        search();
        return view;
    }

//    private void searchViewTest() {
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//
//                return true;
//            }
//        });
//    }
//    private void searchContact(String keyword) {
//        List<Story> storyList = mDBHelper.search(keyword);
//        if (storyList != null) {
//            recyclerView.setAdapter(new ContactListAdapter(getApplicationContext(), contacts));
//        }
//    }
    private void search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            boolean search;
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Story>newList = new ArrayList<>();
                for (Story story : mStoryList){
                    String name = story.getName().toLowerCase();
                    if (name.contains(newText)){
                        newList.add(story);
                    }
                }
                adapter = new StoryAdapter(getActivity(), (ArrayList<Story>) mStoryList);
                adapter.setFilter(newList);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}