package com.example.projectappdoctruyen.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Adapter.StoryAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import java.util.*

class SearchFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adapter: StoryAdapter? = null
    private var mStoryList: MutableList<Story>? = null
    private var mDBHelper: DatabaseHelper? = null
    private var searchView: SearchView? = null
    private var txtNoData: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.search_barView)
        recyclerView = view.findViewById(R.id.recyclerView_Search)
        txtNoData = view.findViewById(R.id.txtNoData)
        mDBHelper = DatabaseHelper(activity)
        mStoryList = mDBHelper!!.story
        adapter = StoryAdapter(requireActivity(), mStoryList!!)
        //        adapter.setFilter(newList);
        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = adapter
        adapter!!.notifyDataSetChanged()
        search()
        return view
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
    private fun search() {
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            var search = false
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                var newText = newText
                newText = newText.toLowerCase()
                val newList = ArrayList<Story>()
                for (story in mStoryList!!) {
                    val name = story!!.name!!.toLowerCase()
                    if (name!!.contains(newText)) {
                        newList.add(story)
                    }
                }
                adapter = StoryAdapter(requireActivity(), mStoryList!!)
                adapter!!.setFilter(newList!!)
                linearLayoutManager = LinearLayoutManager(activity)
                recyclerView!!.layoutManager = linearLayoutManager
                recyclerView!!.setHasFixedSize(true)
                recyclerView!!.adapter = adapter
                adapter!!.notifyDataSetChanged()
                return true
            }
        })
    }
}