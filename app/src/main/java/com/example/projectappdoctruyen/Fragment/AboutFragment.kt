package com.example.projectappdoctruyen.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectappdoctruyen.Activity.SettingActivity
import com.example.projectappdoctruyen.Activity.StoryWatchedActivity
import com.example.projectappdoctruyen.R
import com.example.projectappdoctruyen.Service.CheckConnection
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class AboutFragment : Fragment(), View.OnClickListener {
    private var view1: View? = null
    private var templateView: TemplateView? = null
    private val url_Security = "http://simbo.xyz/privacy.html"
    private val url_ShareApp = "https://play.google.com/store/apps/details?id=com.zing.mp3&hl=vi"
    private val url_FeedBack = "https://play.google.com/store/apps/details?id=com.zing.mp3&hl=vi"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view1 = inflater.inflate(R.layout.fragment_about, container, false)
        initView()
        checkConnectionAds()
        return view1
    }

    private fun checkConnectionAds() {
        if (CheckConnection.isConnectedtoInternet(activity)) {
            setAdsView()
        } else {
            templateView!!.visibility = View.GONE
        }
    }

    private fun initView() {
        templateView = view1!!.findViewById(R.id.template_AboutFragment)
        view1!!.findViewById<View>(R.id.linearStoryWatched).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.linearSetting).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.linearFeedBack).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.linearShareApp).setOnClickListener(this)
        view1!!.findViewById<View>(R.id.linearSecurity).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.linearStoryWatched -> {
                val intentStoryWatched = Intent(activity, StoryWatchedActivity::class.java)
                startActivity(intentStoryWatched)
            }
            R.id.linearSetting -> {
                val intentSetting = Intent(context, SettingActivity::class.java)
                startActivity(intentSetting)
            }
            R.id.linearFeedBack -> {
                val intentFeedBack = Intent(Intent.ACTION_VIEW)
                intentFeedBack.data = Uri.parse(url_FeedBack)
                startActivity(intentFeedBack)
            }
            R.id.linearShareApp -> {
                val appPackageName = requireActivity().packageName
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, url_ShareApp + appPackageName)
                sendIntent.type = "text/plain"
               requireActivity().startActivity(sendIntent)
            }
            R.id.linearSecurity -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url_Security)
                startActivity(i)
            }
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
}