package ru.calcs.trianglecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMobileAdsYandex()
        loadAndShowBanner()
    }





    fun initMobileAdsYandex() {
        MobileAds.initialize(this) { Log.d("MyLog", "SDK Initialised OK") }
    }

    fun loadAndShowBanner(){
        adViewYandex.apply {
            setAdSize(AdSize.BANNER_320x50)
            setAdUnitId(getString(R.string.yandex_banner_id_test))
        }
        val adRequest = AdRequest.Builder().build()
        adViewYandex.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {
                Log.d("MyLog", "Ad Loaded Ok")
            }
            override fun onAdFailedToLoad(p0: AdRequestError) {
                Log.d("MyLog", "Banner Ad Load Fail : ${p0.toString()}")
            }
            override fun onAdClicked() {
                Log.d("MyLog", "Ad Clicked")
            }
            override fun onLeftApplication() {
            }
            override fun onReturnedToApplication() {
            }
            override fun onImpression(p0: ImpressionData?) {
            }
        })
        adViewYandex.loadAd(adRequest)
    }


}
