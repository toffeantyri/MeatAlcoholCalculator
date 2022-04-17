package ru.calcs.meatcalculator.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.yandex.mobile.ads.common.*
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.adapters.AppPreference
import ru.calcs.meatcalculator.viewmodel.DataModelView
import java.lang.StringBuilder

const val TAG = "MyLog"

class BottomSheetFragment : Fragment() {

    var myInterStitialAd: InterstitialAd? = null

    val dataModel: DataModelView by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view0 = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)


        myInterStitialAd = InterstitialAd(requireContext())
        initAndloadInterStitialAd()


        //наблюдатель итого на Х человек мясо
        dataModel.main_titleResult_x_people_meat.observe(this as LifecycleOwner) {
            val peopleTextCount =
                if (it != null && it.toInt() > 1) getString(R.string.RESULT_DESC_ITOGO_peoples) else getString(R.string.RESULT_DESC_ITOGO_people)

            if (it != null && it.toInt() > 0) {
                view0.main_title_x_people_meat.text =
                    (getString(R.string.RESULT_DESC_ITOGO1) + it.toInt()
                        .toString() + peopleTextCount + getString(R.string.RESULT_DESC_ITOGO2))
            }
        }
        dataModel.meatValueIsNoEmpty.observe(this as LifecycleOwner) {
            if (it) {
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        //наблюдатель итого на Х человек алко
        dataModel.main_titleResult_x_people_alco.observe(this as LifecycleOwner) {
            val peopleTextCount =
                if (it != null && it.toInt() > 1) getString(R.string.RESULT_DESC_ITOGO_peoples) else getString(R.string.RESULT_DESC_ITOGO_people)

            if (it != null && it.toInt() > 0) {
                view0.main_title_x_people_alco.text =
                    (getString(R.string.RESULT_DESC_ITOGO1) + it.toInt()
                        .toString() + peopleTextCount + getString(R.string.RESULT_DESC_ITOGO2))
            }

        }

        dataModel.alcoValueIsNoEmpty.observe(this as LifecycleOwner) {
            if (it) {
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }


        dataModel.result_value_fish.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_fish_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.fishs))
                view0.container_fish_result.visibility = View.VISIBLE
            } else {
                view0.container_fish_result.visibility = View.GONE
            }
        }

        dataModel.result_value_chicken.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_chiken_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.chickens))
                view0.container_chicken_result.visibility = View.VISIBLE
            } else {
                view0.container_chicken_result.visibility = View.GONE
            }
        }

        dataModel.result_value_pig.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_pig_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.pigs))
                view0.container_pig_result.visibility = View.VISIBLE
            } else {
                view0.container_pig_result.visibility = View.GONE
            }
        }

        dataModel.result_value_muu.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_muu_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.muus))
                view0.container_muu_result.visibility = View.VISIBLE
            } else {
                view0.container_muu_result.visibility = View.GONE
            }
        }

        dataModel.result_value_bear.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_bear_litr.text =
                    (String.format("%.2f", it) + getString(R.string.litrs) + getString(R.string.bears))
                view0.container_bear_result.visibility = View.VISIBLE
            } else {
                view0.container_bear_result.visibility = View.GONE
            }
        }

        dataModel.result_value_vine.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_vine_litr.text =
                    (String.format("%.2f", it) + getString(R.string.litrs) + getString(R.string.vines))
                view0.container_vine_result.visibility = View.VISIBLE
            } else {
                view0.container_vine_result.visibility = View.GONE
            }
        }

        dataModel.result_value_vodka.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_vodka_litr.text =
                    (String.format("%.2f", it) + getString(R.string.litrs) + getString(R.string.vodkas))
                view0.container_vodka_result.visibility = View.VISIBLE
            } else {
                view0.container_vodka_result.visibility = View.GONE
            }
        }

        dataModel.result_value_bread.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_bread.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.breads))
                view0.container_bread.visibility = View.VISIBLE
            } else view0.container_bread.visibility = View.GONE
        }

        dataModel.result_value_veget.observe(this as LifecycleOwner) {
            if (it != null && it != 0f) {
                view0.tvResult_value_vegetable.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.vegetables))
                view0.container_vegetable.visibility = View.VISIBLE
            } else view0.container_vegetable.visibility = View.GONE
        }


        view0.btn_save_result.setOnClickListener {
            val fish: Float = dataModel.result_value_fish.value ?: 0f
            val chicken: Float = dataModel.result_value_chicken.value ?: 0f
            val pig: Float = dataModel.result_value_pig.value ?: 0f
            val muu: Float = dataModel.result_value_muu.value ?: 0f
            val bear: Float = dataModel.result_value_bear.value ?: 0f
            val vine: Float = dataModel.result_value_vine.value ?: 0f
            val vodka: Float = dataModel.result_value_vodka.value ?: 0f
            val bread: Float = dataModel.result_value_bread.value ?: 0f
            val veget: Float = dataModel.result_value_veget.value ?: 0f
            val xPeopleMeat: Float = dataModel.main_titleResult_x_people_meat.value ?: 0f
            val xPeopleAlco: Float = dataModel.main_titleResult_x_people_alco.value ?: 0f
            saveOnClick(fish, chicken, pig, muu, bear, vine, vodka, bread, veget, xPeopleMeat, xPeopleAlco)
            if (myInterStitialAd?.isLoaded!!) {
                myInterStitialAd?.show()
            }
        }

        view0.btn_load_result.setOnClickListener {
            loadOnClick()
        }

        view0.btn_share_result.setOnClickListener {
            val fish: Float = dataModel.result_value_fish.value ?: 0f
            val chicken: Float = dataModel.result_value_chicken.value ?: 0f
            val pig: Float = dataModel.result_value_pig.value ?: 0f
            val muu: Float = dataModel.result_value_muu.value ?: 0f
            val bear: Float = dataModel.result_value_bear.value ?: 0f
            val vine: Float = dataModel.result_value_vine.value ?: 0f
            val vodka: Float = dataModel.result_value_vodka.value ?: 0f
            val bread: Float = dataModel.result_value_bread.value ?: 0f
            val veget: Float = dataModel.result_value_veget.value ?: 0f
            val xPeopleMeat: Float = dataModel.main_titleResult_x_people_meat.value ?: 0f
            val xPeopleAlco: Float = dataModel.main_titleResult_x_people_alco.value ?: 0f
            shareOnClick(fish, chicken, pig, muu, bear, vine, vodka, bread, veget, xPeopleMeat, xPeopleAlco)
        }

        view0.btn_clear_res.setOnClickListener {
            clearResultOnClick()
        }
        return view0
    }


    fun saveOnClick(
        fish: Float,
        chicken: Float,
        pig: Float,
        muu: Float,
        bear: Float,
        vine: Float,
        vodka: Float,
        bread: Float,
        veget: Float,
        xPeopleMeat: Float,
        xPeopleAlco: Float
    ) {
        val context = context ?: requireActivity().parent
        val pref = AppPreference(context)
        pref.saveResult1(fish, chicken, pig, muu, bear, vine, vodka, bread, veget, xPeopleMeat, xPeopleAlco)
    }

    fun loadOnClick() {
        val context = context ?: requireActivity().parent
        val pref = AppPreference(context)
        val map = pref.loadResult1()
        dataModel.result_value_fish.value = map.getValue("fish")
        dataModel.result_value_chicken.value = map.getValue("chicken")
        dataModel.result_value_pig.value = map.getValue("pig")
        dataModel.result_value_muu.value = map.getValue("muu")
        dataModel.result_value_bear.value = map.getValue("bear")
        dataModel.result_value_vine.value = map.getValue("vine")
        dataModel.result_value_vodka.value = map.getValue("vodka")
        dataModel.result_value_bread.value = map.getValue("bread")
        dataModel.result_value_veget.value = map.getValue("vegetable")
        dataModel.main_titleResult_x_people_meat.value = map.getValue("people_count_meat")
        dataModel.main_titleResult_x_people_alco.value = map.getValue("people_count_alco")

        dataModel.meatValueIsNoEmpty.value =
            if (dataModel.result_value_fish.value!! > 0f || dataModel.result_value_chicken.value!! > 0f || dataModel.result_value_pig.value!! > 0f || dataModel.result_value_muu.value!! > 0f) true else false
        dataModel.alcoValueIsNoEmpty.value =
            if (dataModel.result_value_bear.value!! > 0f || dataModel.result_value_vine.value!! > 0f || dataModel.result_value_vodka.value!! > 0f) true else false
    }


    fun shareOnClick(
        fish: Float,
        chicken: Float,
        pig: Float,
        muu: Float,
        bear: Float,
        vine: Float,
        vodka: Float,
        bread: Float,
        veget: Float,
        xPeopleMeat: Float,
        xPeopleAlco: Float
    ) {
        val intent = Intent()
        val myString = StringBuilder()
        myString.apply {
            if (xPeopleMeat != 0f) {
                append(
                    getString(R.string.RESULT_DESC_ITOGO1) + xPeopleMeat.toInt()
                        .toString() + getString(R.string.RESULT_DESC_ITOGO_peoples) + "," + getString(R.string.RESULT_DESC_ITOGO2) + "\n"
                )
            }
            if (fish != 0f) {
                append(String.format("%.2f", fish) + getString(R.string.kg) + getString(R.string.fishs) + "\n")
            }
            if (chicken != 0f) {
                append(String.format("%.2f", chicken) + getString(R.string.kg) + getString(R.string.chickens) + "\n")
            }
            if (pig != 0f) {
                append(String.format("%.2f", pig) + getString(R.string.kg) + getString(R.string.pigs) + "\n")
            }
            if (muu != 0f) {
                append(String.format("%.2f", muu) + getString(R.string.kg) + getString(R.string.muus) + "\n")
            }

            if (bread != 0f) {
                append(
                    String.format(
                        "%.0f",
                        bread * 1000
                    ) + getString(R.string.gramm) + getString(R.string.breads) + "\n"
                )
            }
            if (veget != 0f) {
                append(String.format("%.2f", veget) + getString(R.string.kg) + getString(R.string.vegetables) + "\n")
            }

            if (xPeopleAlco != 0f) {
                append(
                    getString(R.string.RESULT_DESC_ITOGO1) + xPeopleAlco.toInt()
                        .toString() + getString(R.string.RESULT_DESC_ITOGO_peoples) + "," + getString(
                        R.string.RESULT_DESC_ITOGO2_1
                    ) + getString(R.string.RESULT_DESC_ITOGO3_1_alco) + "\n"
                )
            }
            if (bear != 0f) {
                append(String.format("%.2f", bear) + getString(R.string.litrs) + getString(R.string.bears) + "\n")
            }
            if (vine != 0f) {
                append(String.format("%.2f", vine) + getString(R.string.litrs) + getString(R.string.vines) + "\n")
            }
            if (vodka != 0f) {
                append(String.format("%.2f", vodka) + getString(R.string.litrs) + getString(R.string.vodkas) + "\n")
            }
        }

        if (myString.toString() != "") {
            val myString2 = StringBuilder().apply {
                append(getString(R.string.share_text_1))
                append(getString(R.string.link_on_app) + "\n")
                append(myString.toString())
            }
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, myString2.toString())
            intent.setType("text/plain")
            Log.d(TAG, myString2.toString())
            startActivity(Intent.createChooser(intent, getString(R.string.btn_share)))
        } else Toast.makeText(context, R.string.no_result, Toast.LENGTH_SHORT).show()


    }

    fun clearResultOnClick() {
        dataModel.apply {

            main_titleResult_x_people_meat.value = 0f
            main_titleResult_x_people_alco.value = 0f

            meatValueIsNoEmpty.value = false
            alcoValueIsNoEmpty.value = false

            result_value_fish.value = 0f
            result_value_chicken.value = 0f
            result_value_pig.value = 0f
            result_value_muu.value = 0f

            result_value_bread.value = 0f
            result_value_veget.value = 0f

            result_value_bear.value = 0f
            result_value_vine.value = 0f
            result_value_vodka.value = 0f


        }
    }


    fun initAndloadInterStitialAd() {
        myInterStitialAd?.setAdUnitId(getString(R.string.yandex_interstitial_id))
        myInterStitialAd?.setInterstitialAdEventListener(object : InterstitialAdEventListener {
            override fun onAdLoaded() {
                Log.d(TAG, "InterStitial Ad is Loaded Succesfull")
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                Log.d(TAG, "InterStitial Ad Failed toLoad: ${p0.description}")
            }

            override fun onAdShown() {
            }

            override fun onAdDismissed() {
            }

            override fun onAdClicked() {
            }

            override fun onLeftApplication() {
            }

            override fun onReturnedToApplication() {
            }

            override fun onImpression(p0: ImpressionData?) {
            }

        })
        val adRequest: AdRequest = AdRequest.Builder().build()
        myInterStitialAd?.loadAd(adRequest)
    }


}
