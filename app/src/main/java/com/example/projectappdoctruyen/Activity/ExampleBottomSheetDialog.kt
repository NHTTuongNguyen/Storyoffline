package com.example.projectappdoctruyen.Activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Adapter.ChapterListBottomItemAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Chapter
import com.example.projectappdoctruyen.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExampleBottomSheetDialog : BottomSheetDialogFragment() {
    private var chapterArrayList: List<Chapter?>? = null
    private var databaseHelper: DatabaseHelper? = null
    private var chapter: Chapter? = null
    private lateinit var adapter: ChapterListBottomItemAdapter
    private lateinit var recyclerView_Item: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.item_bottomlist, container, false)
        recyclerView_Item = v.findViewById(R.id.recyclerView_Item)
        dataIntent()
        databaseHelper = DatabaseHelper(requireActivity())
        chapterArrayList = databaseHelper!!.getChapTer(chapter!!.idStory.toString())
        adapter = ChapterListBottomItemAdapter(requireContext(), chapterArrayList,this@ExampleBottomSheetDialog)
//        val linearLayoutManager = LinearLayoutManager(activity)
        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        val gridLayoutManager = GridLayoutManager(requireActivity(), 5)
//        val adapter_khoanchi = Adapter_Khoanchi(activity, kc, this@KhoangchiFragment)

        recyclerView_Item!!.layoutManager = gridLayoutManager
        recyclerView_Item!!.setHasFixedSize(true)
        recyclerView_Item!!.adapter = adapter
        adapter!!.notifyDataSetChanged()
        return v
    }
    private fun dataIntent() {
        val intent = requireActivity().intent
        if (intent != null) {
            chapter = intent.getSerializableExtra("Chapter") as Chapter?
            Log.d("DFAS", chapter!!.title.toString())
        }
    }
}