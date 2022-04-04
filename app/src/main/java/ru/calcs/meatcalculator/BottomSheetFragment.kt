package ru.calcs.meatcalculator

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import ru.calcs.meatcalculator.viewmodel.DataModelView
import kotlin.properties.Delegates

// алко
//coefType = when (toggleTitle1) {
//    listIdColumnRadio1[0] -> 1f   //любое
//    listIdColumnRadio1[1] -> 1.5f   // пиво
//    listIdColumnRadio1[2] -> 0.5f    //вино
//    listIdColumnRadio1[3] -> 0.3f   //крепкое
//    else -> 1.0f
//}

//мясо
//coefType = when (toggleTitle1) {
//    listIdColumnRadio1[0] -> 1.3f   //любое
//    listIdColumnRadio1[1] -> 1.3f   // кура
//    listIdColumnRadio1[2] -> 1.35f    //свин
//    listIdColumnRadio1[3] -> 1.4f   //говядин
//    //listIdColumnRadio1[4] -> {1.2f} // рыба
//    else -> 1.3f
//}
//countProd = String.format("%.2f",0.3f*peopleCount*coefType*coefTime)
class BottomSheetFragment : Fragment() {

    companion object {
    }

    val dataModel: DataModelView by activityViewModels()
    var position: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view0 = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        dataModel.positionRC.observe(this as LifecycleOwner) {
            position = it
        }

        //наблюдатель итого на Х человек мясо
        dataModel.main_titleResult_x_people_meat.observe(this as LifecycleOwner) {
            val peopleTextCount =
                if (it.toInt() > 1) getString(R.string.RESULT_DESC_ITOGO_peoples) else getString(R.string.RESULT_DESC_ITOGO_people)
            view0.main_title_x_people_meat.text =
                (getString(R.string.RESULT_DESC_ITOGO1) + it + peopleTextCount + getString(R.string.RESULT_DESC_ITOGO2))
        }

        //наблюдатель итого на Х человек алко
        dataModel.main_titleResult_x_people_alco.observe(this as LifecycleOwner) {
            val peopleTextCount =
                if (it.toInt() > 1) getString(R.string.RESULT_DESC_ITOGO_peoples) else getString(R.string.RESULT_DESC_ITOGO_people)
            view0.main_title_x_people_alco.text =
                (getString(R.string.RESULT_DESC_ITOGO1) + it + peopleTextCount + getString(R.string.RESULT_DESC_ITOGO2))
        }

        dataModel.result_value_meat.observe(this as LifecycleOwner) {
            if (it != null) {
                dataModel.result_value_chicken.value = it / 3 * 1.3f
                dataModel.result_value_pig.value = it / 3 * 1.35f
                dataModel.result_value_muu.value = it / 3 * 1.4f
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        dataModel.result_value_chicken.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_chiken_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.chickens))
                view0.container_chicken_result.visibility = View.VISIBLE
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.container_chicken_result.visibility = View.GONE
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        dataModel.result_value_pig.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_pig_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.pigs))
                view0.container_pig_result.visibility = View.VISIBLE
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.container_pig_result.visibility = View.GONE
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        dataModel.result_value_muu.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_muu_kg.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.muus))
                view0.container_muu_result.visibility = View.VISIBLE
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.container_muu_result.visibility = View.GONE
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        dataModel.result_value_alco.observe(this as LifecycleOwner) {
            if (it != null) {
                dataModel.result_value_bear.value = it / 3 * 1.5f
                dataModel.result_value_vine.value = it / 3 * 0.5f
                dataModel.result_value_vodka.value = it / 3 * 0.3f
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }

        dataModel.result_value_bear.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_bear_litr.text = (String.format(
                    "%.2f",
                    it
                ) + getString(R.string.litrs) + getString(R.string.bears))
                view0.container_bear_result.visibility = View.VISIBLE
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.container_bear_result.visibility = View.GONE
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }

        dataModel.result_value_vine.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_vine_litr.text =
                    (String.format("%.2f", it) + getString(R.string.litrs) + getString(R.string.vines))
                view0.container_vine_result.visibility = View.VISIBLE
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.container_vine_result.visibility = View.GONE
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }

        dataModel.result_value_vodka.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_vodka_litr.text =
                    (String.format("%.2f", it) + getString(R.string.litrs) + getString(R.string.vodkas))
                view0.container_vodka_result.visibility = View.VISIBLE
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.container_vodka_result.visibility = View.GONE
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }


        dataModel.result_value_bread.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_bread.text = (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.breads))
                view0.container_bread.visibility = View.VISIBLE
            } else view0.container_bread.visibility = View.GONE
        }

        dataModel.result_value_veget.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_vegetable.text = (String.format("%.2f", it) + getString(R.string.kg)+ getString(R.string.vegetables))
                view0.container_vegetable.visibility = View.VISIBLE
            } else view0.container_vegetable.visibility = View.GONE
        }



        return view0
    }


}
