package ru.calcs.meatcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import ru.calcs.meatcalculator.adapters.ShablonDataList
import ru.calcs.meatcalculator.adapters.TopAdapter
import ru.calcs.meatcalculator.viewmodel.DataModelView

class MainActivity : AppCompatActivity() {

    val TAG = "MyLog"
    lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>
    lateinit var adapterTop: TopAdapter
    val dataModel: DataModelView by viewModels()
    val listRc : ArrayList<ShablonDataList> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMobileAdsYandex()
        loadAndShowBanner()
        bottomSheetBehavior = BottomSheetBehavior.from(main_bottom_sheets)
        dataModel.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_HIDDEN
        dataModel.stateBottomSheetBehavior.observe(this, { bottomSheetBehavior.state = it })

        Thread{


        }.run()


        Log.d(TAG, "onCreate")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "OnResume")
        super.onResume()
        Thread {
            initTopRV()
            if (supportFragmentManager.findFragmentById(R.id.bottom_sheet_frame) == null) {
                Log.d(TAG, "Fragment replased")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.bottom_sheet_frame, ru.calcs.meatcalculator.BottomSheetFragment())
                    .commit()
            }
        }.run()
    }


    fun initMobileAdsYandex() {
        MobileAds.initialize(this) { Log.d("MyLog", "SDK Initialised OK") }
    }

    fun loadAndShowBanner() {
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

    var index = 0
    fun onClickBtnTestBottomSheet() {
        when {
            index == 0 -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            index == 1 -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            index == 2 -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
            index == 3 -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        Log.d("MyLog", index.toString())
        index++
        if (index == 4) {
            index = 0
        }
    }

    fun initTopRV() {
        adapterTop = TopAdapter(dataModel)
        rcView_TopSelector.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterTop
            setHasFixedSize(true)
        }
    }


    fun ArrayList<ShablonDataList>.builderListOfShablonClass() : ArrayList<ShablonDataList>{
        val l1 = ShablonDataList()
        val l2 = ShablonDataList()
    l1.apply {
        mainTitle = getString(R.string.title_item_rctop)
        column1Title = getString(R.string.title_column1)
        column2Title = getString(R.string.title_column2)

        c1radio1 = getString(R.string.all_meat)
        c1radio2 = getString(R.string.chicken)
        c1radio3 = getString(R.string.pig)
        c1radio4 = getString(R.string.muu)

        c1radio1Image = R.drawable.meatall
        c1radio2Image = R.drawable.chicken1
        c1radio3Image = R.drawable.pig1
        c1radio4Image = R.drawable.muu1

        c2radio1 = getString(R.string.time_low)
        c2radio2 = getString(R.string.time_med)
        c2radio3 = getString(R.string.time_max)
        c2radio4 = getString(R.string.time_over)
    }
        l2.apply {
            mainTitle = getString(R.string.title_item_rctop_alco)
            column1Title = getString(R.string.title_column1_alco)
            column2Title = getString(R.string.title_column2_alco)

            c1radio1 = getString(R.string.all_alco)
            c1radio2 = getString(R.string.bear)
            c1radio3 = getString(R.string.vine)
            c1radio4 = getString(R.string.vodka)

            c1radio1Image = R.drawable.allalco
            c1radio2Image = R.drawable.bear1
            c1radio3Image = R.drawable.vine
            c1radio4Image = R.drawable.cognac

            c2radio1 = getString(R.string.alco_low)
            c2radio2 = getString(R.string.alco_med)
            c2radio3 = getString(R.string.alco_max)
            c2radio4 = getString(R.string.alco_over)
        }
        return arrayListOf(l1,l2)
    }



}
