package ru.calcs.meatcalculator


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import ru.calcs.meatcalculator.viewmodel.DataModelView

//КОЭФИЦИЕНТ УЖАРКИ
const val COEF_CHIKEN = 1.3F
const val COEF_PIG = 1.35f
const val COEF_MUU = 1.4f

//СКОЛЬКО ДЛЯ 1 ОПЬЯНЕНИЯ (типа порция на пару часов)
const val COEF_BEAR = 1.4f
const val COEF_VINE = 0.7f
const val COEF_VODKA = 0.25f


class BottomSheetFragment : Fragment() {

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
                dataModel.result_value_chicken.value = (it / 3) * COEF_CHIKEN
                dataModel.result_value_pig.value = (it / 3) * COEF_PIG
                dataModel.result_value_muu.value = (it / 3) * COEF_MUU
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        dataModel.result_value_chicken.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_chiken_kg.text =
                    (String.format("%.2f", it * COEF_CHIKEN) + getString(R.string.kg) + getString(R.string.chickens))
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
                    (String.format("%.2f", it * COEF_PIG) + getString(R.string.kg) + getString(R.string.pigs))
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
                    (String.format("%.2f", it * COEF_MUU) + getString(R.string.kg) + getString(R.string.muus))
                view0.container_muu_result.visibility = View.VISIBLE
                view0.main_title_x_people_meat.visibility = View.VISIBLE
            } else {
                view0.container_muu_result.visibility = View.GONE
                view0.main_title_x_people_meat.visibility = View.GONE
            }
        }

        dataModel.result_value_alco.observe(this as LifecycleOwner) {
            if (it != null) {
                dataModel.result_value_bear.value = (it * COEF_BEAR) * 0.2f
                dataModel.result_value_vine.value = (it * COEF_VINE) * 0.25f
                dataModel.result_value_vodka.value = (it * COEF_VODKA) * 0.55f
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }

        dataModel.result_value_bear.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_bear_litr.text = (String.format(
                    "%.2f",
                    it * COEF_BEAR
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
                    (String.format("%.2f", it * COEF_VINE) + getString(R.string.litrs) + getString(R.string.vines))
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
                    (String.format("%.2f", it * COEF_VODKA) + getString(R.string.litrs) + getString(R.string.vodkas))
                view0.container_vodka_result.visibility = View.VISIBLE
                view0.main_title_x_people_alco.visibility = View.VISIBLE
            } else {
                view0.container_vodka_result.visibility = View.GONE
                view0.main_title_x_people_alco.visibility = View.GONE
            }
        }


        dataModel.result_value_bread.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_bread.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.breads))
                view0.container_bread.visibility = View.VISIBLE
            } else view0.container_bread.visibility = View.GONE
        }

        dataModel.result_value_veget.observe(this as LifecycleOwner) {
            if (it != null) {
                view0.tvResult_value_vegetable.text =
                    (String.format("%.2f", it) + getString(R.string.kg) + getString(R.string.vegetables))
                view0.container_vegetable.visibility = View.VISIBLE
            } else view0.container_vegetable.visibility = View.GONE
        }



        return view0
    }


}
