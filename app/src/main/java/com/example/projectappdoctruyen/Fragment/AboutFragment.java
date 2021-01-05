package com.example.projectappdoctruyen.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectappdoctruyen.Activity.SettingActivity;
import com.example.projectappdoctruyen.Activity.StoryWatchedActivity;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Service.CheckConnection;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class AboutFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TemplateView templateView;
    private  final String url_Security = "http://simbo.xyz/privacy.html";
    private  final String url_ShareApp = "https://play.google.com/store/apps/details?id=com.zing.mp3&hl=vi";
    private  final String url_FeedBack = "https://play.google.com/store/apps/details?id=com.zing.mp3&hl=vi";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view  =  inflater.inflate(R.layout.fragment_about, container, false);
        initView();
        checkConnectionAds();
        return  view;
    }
    private void checkConnectionAds() {
        if (CheckConnection.isConnectedtoInternet(getActivity())){
            setAdsView();
        }else {
            templateView.setVisibility(View.GONE);
        }
    }

    private void initView() {
        templateView =view.findViewById(R.id.template_AboutFragment);
        view.findViewById(R.id.linearStoryWatched).setOnClickListener(this);
        view.findViewById(R.id.linearSetting).setOnClickListener(this);
        view.findViewById(R.id.linearFeedBack).setOnClickListener(this);
        view.findViewById(R.id.linearShareApp).setOnClickListener(this);
        view.findViewById(R.id.linearSecurity).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearStoryWatched:
                Intent intentStoryWatched = new Intent(getActivity(), StoryWatchedActivity.class);
                startActivity(intentStoryWatched);
                break;
            case R.id.linearSetting:
                Intent intentSetting = new Intent(getContext(), SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.linearFeedBack:
                Intent intentFeedBack = new Intent(Intent.ACTION_VIEW);
                intentFeedBack.setData(Uri.parse(url_FeedBack));
                startActivity(intentFeedBack);
                break;
            case R.id.linearShareApp:
                final String appPackageName = getActivity().getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, url_ShareApp + appPackageName);
                sendIntent.setType("text/plain");
                getActivity().startActivity(sendIntent);
                break;
            case R.id.linearSecurity:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url_Security));
                startActivity(i);
                break;
        }
    }
    private void setAdsView() {
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdLoader.Builder builder = new AdLoader.Builder(getActivity(),getString(R.string.Ads_appId));
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
}