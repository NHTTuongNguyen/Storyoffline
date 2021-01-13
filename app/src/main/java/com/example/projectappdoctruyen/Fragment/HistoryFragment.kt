package com.example.projectappdoctruyen.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectappdoctruyen.Adapter.HistoryFragmentsAdapter
import com.example.projectappdoctruyen.Model.Story
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Service.CheckConnection
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import java.util.*

class HistoryFragment : Fragment() {
    private var view1: View? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: HistoryFragmentsAdapter? = null
    private var storyList: ArrayList<Story?>? = null
    private var txtNodataHistory: TextView? = null
    private var sharedPreferencesUtils: SharedPreferences_Utils? = null
    private var templateView: TemplateView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view1 = inflater.inflate(R.layout.fragment_history, container, false)
        initView()
        storyList = sharedPreferencesUtils!!.get_SharedPreferences_Story_HistoryFragment()
        buildRecyclerview()
        checkConnectionAds()
        return view1
    }

    private fun initView() {
        sharedPreferencesUtils = SharedPreferences_Utils(requireActivity())
        txtNodataHistory = view1!!.findViewById(R.id.txtNodataHistory)
        recyclerView = view1!!.findViewById(R.id.recyclerView_Docganday)
        templateView = view1!!.findViewById(R.id.template_HistoryFragment)
    }

    private fun checkConnectionAds() {
        if (CheckConnection.isConnectedtoInternet(activity)) {
            setAdsView()
        } else {
            templateView!!.visibility = View.GONE
        }
    }

    private fun setAdsView() {
        MobileAds.initialize(activity) { }
        val builder = AdLoader.Builder(activity, getString(R.string.Ads_appId))
        builder.forUnifiedNativeAd { unifiedNativeAd -> templateView!!.setNativeAd(unifiedNativeAd) }
        val adLoader = builder.build()
        val adRequest = AdRequest.Builder().build()
        adLoader.loadAd(adRequest)
    }

    private fun buildRecyclerview() {
        if (storyList!!.size > 0) {
            adapter = HistoryFragmentsAdapter(activity, storyList)
            linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView!!.layoutManager = linearLayoutManager
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.adapter = adapter
            recyclerView!!.visibility = View.VISIBLE
            txtNodataHistory!!.visibility = View.GONE
            adapter!!.notifyDataSetChanged()
        } else {
            txtNodataHistory!!.visibility = View.VISIBLE
            recyclerView!!.visibility = View.GONE
        }
    }
}