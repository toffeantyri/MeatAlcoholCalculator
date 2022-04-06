package ru.calcs.meatcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.InitializationListener
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.calcs.meatcalculator.adapters.ShablonDataList
import ru.calcs.meatcalculator.adapters.TopAdapter
import ru.calcs.meatcalculator.viewmodel.DataModelView

class MainActivity : AppCompatActivity() {

    val TAG = "MyLog"
    lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>
    lateinit var adapterTop: TopAdapter
    val dataModel: DataModelView by viewModels()
    val listRc: ArrayList<ShablonDataList> = arrayListOf()
    private var job: Job? = null // для корутины на запуске

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTopRV()
        initMobileAdsYandex()
        setUpBanner()
        bottomSheetBehavior = BottomSheetBehavior.from(main_bottom_sheets)


        Log.d(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        dataModel.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_COLLAPSED
        dataModel.stateBottomSheetBehavior.observe(this, { bottomSheetBehavior.state = it })
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "OnResume")
        super.onResume()
        loadAndShowBanner()
        job = CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "Coroutine start: $job")

            if (adapterTop.list.isEmpty()) {
                listRc.builderListOfShablonClass()
                adapterTop.updateAdapter(listRc)
            }

            if (supportFragmentManager.findFragmentById(R.id.bottom_sheet_frame) == null) {
                Log.d(TAG, "inCoroutine : Fragment replased")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.bottom_sheet_frame, BottomSheetFragment())
                    .commit()
            }
        }
    }


    fun initMobileAdsYandex() {
        MobileAds.initialize(this) {
            InitializationListener { Log.d("MyLog", "SDK yandex Initialised OK") }
        }
    }

    fun setUpBanner(){
        adViewYandex.apply {
            setAdSize(AdSize.BANNER_320x50)
            setAdUnitId(getString(R.string.yandex_banner_id_test))
        }
    }

    fun loadAndShowBanner() {
        val adRequest = AdRequest.Builder().build()
        adViewYandex.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {
                Log.d("MyLog", "Ad Banner Loaded Ok")
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                Log.d("MyLog", "Banner Ad Loading Failed : ${p0.toString()}")
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

    fun initTopRV() {
        if (this::adapterTop.isLateinit) {
            adapterTop = TopAdapter(dataModel)
        }
        if (rcView_TopSelector.isEmpty()) {
            Log.d(TAG, "init rc")
            rcView_TopSelector.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterTop
                setHasFixedSize(true)
            }
        }
    }

    fun ArrayList<ShablonDataList>.builderListOfShablonClass() {
        val l1 = ShablonDataList("0")
        val l2 = ShablonDataList("1")

        l1.apply {
            mainTitle = getString(R.string.title_item_rctop)
            column1Title = getString(R.string.title_column1)
            column2Title = getString(R.string.title_column2)

            c1radio1 = getString(R.string.fish)
            c1radio2 = getString(R.string.chicken)
            c1radio3 = getString(R.string.pig)
            c1radio4 = getString(R.string.muu)

            c1radio1Image = R.drawable.ic_fish
            c1radio2Image = R.drawable.ic_chicken
            c1radio3Image = R.drawable.ic_pig
            c1radio4Image = R.drawable.ic_muu

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

            c1radio1Image = R.drawable.ic_allalco
            c1radio2Image = R.drawable.ic_bear
            c1radio3Image = R.drawable.ic_vine
            c1radio4Image = R.drawable.ic_cognac

            c2radio1 = getString(R.string.alco_low)
            c2radio2 = getString(R.string.alco_med)
            c2radio3 = getString(R.string.alco_max)
            c2radio4 = getString(R.string.alco_over)
        }

        this.add(l1)
        this.add(l2)
    }


}
