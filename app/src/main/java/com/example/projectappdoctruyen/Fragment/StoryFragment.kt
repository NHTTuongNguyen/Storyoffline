package com.example.projectappdoctruyen.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Adapter.StoryAdapter
import com.example.projectappdoctruyen.Database.DatabaseHelper
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import java.io.FileOutputStream
import java.io.OutputStream

class StoryFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adapter: StoryAdapter? = null
    lateinit var mStoryList: MutableList<Story>
    private var mDBHelper: DatabaseHelper? = null
    private val progressbarLoad: ProgressBar? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("lifecyclerView","onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecyclerView","onCreate")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("lifecyclerView","onActivityCreated")

    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecyclerView","onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecyclerView","onResume")

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)
        mDBHelper = DatabaseHelper(activity)
        getDataBaseExits(mDBHelper!!)
        recyclerView = view.findViewById(R.id.recyclerView_Storyfg)
        buildRecyclerView()
        Log.d("lifecyclerView","onCreateView")
        return view
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecyclerView","onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecyclerView","onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifecyclerView","onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecyclerView","onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("lifecyclerView","onDetach")

    }

    private fun buildRecyclerView() {
        mStoryList = mDBHelper!!.story!!
        adapter = StoryAdapter(activity, mStoryList)
        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    private fun getDataBaseExits(mDBHelper: DatabaseHelper) {
        val database = requireActivity().applicationContext.getDatabasePath(DatabaseHelper.Companion.DBNAME)
        if (!database.exists()) {
            mDBHelper.readableDatabase
            //Copy db
            if (copyDatabase(activity)) {
//                Toast.makeText(getActivity(), "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(getActivity(), "Copy data error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun copyDatabase(context: Context?): Boolean {
        return try {
            val inputStream = requireActivity().assets.open(DatabaseHelper.Companion.DBNAME)
            val outFileName: String = DatabaseHelper.Companion.DBLOCATION + DatabaseHelper.Companion.DBNAME
            val outputStream: OutputStream = FileOutputStream(outFileName)
            val buff = ByteArray(1024)
            var length = 0
            while (inputStream.read(buff).also { length = it } > 0) {
                outputStream.write(buff, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            Log.w("MainActivity", "DB copied")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}