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


class BottomSheetFragment : Fragment() {

    companion object {
    }

    val dataModel: DataModelView by activityViewModels()
    var position : Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view0 = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        dataModel.positionRC.observe(this as LifecycleOwner){
            position = it
        }

        //наблюдатель итого на Х человек
        dataModel.main_titleResult_x_people.observe(this as LifecycleOwner){
            val peopleTextCount = if(it.toInt()>1) getString(R.string.RESULT_DESC_ITOGO_peoples) else getString(R.string.RESULT_DESC_ITOGO_people)
            view0.main_title_x_people.text = getString(R.string.RESULT_DESC_ITOGO1) + it + peopleTextCount +getString(R.string.RESULT_DESC_ITOGO2)
        }

        dataModel.result_value_meat.observe(this as LifecycleOwner) {
            view0.tvResult_value_KG.text = it
            if(it != null) view0.container_main_meat_result.visibility = View.VISIBLE
            else view0.container_main_meat_result.visibility = View.GONE
        }

        dataModel.result_value_chicken.observe(this as LifecycleOwner) {
            view0.tvResult_value_chiken_kg.text = it
            if(it != null) view0.container_chicken_result.visibility = View.VISIBLE
            else view0.container_chicken_result.visibility = View.GONE
        }

        dataModel.result_value_pig.observe(this as LifecycleOwner) {
            view0.tvResult_value_pig_kg.text = it
            if(it != null) view0.container_pig_result.visibility = View.VISIBLE
            else view0.container_pig_result.visibility = View.GONE
        }

        dataModel.result_value_muu.observe(this as LifecycleOwner) {
            view0.tvResult_value_muu_kg.text = it
            if(it != null) view0.container_muu_result.visibility = View.VISIBLE
            else view0.container_muu_result.visibility = View.GONE
        }

        dataModel.result_value_alco.observe(this as LifecycleOwner) {
            view0.tvResult_value_LITR.text = it
            if(it != null) view0.container_main_alco_result.visibility = View.VISIBLE
            else view0.container_main_alco_result.visibility = View.GONE
        }

        dataModel.result_value_bear.observe(this as LifecycleOwner) {
            view0.tvResult_value_bear_litr.text = it
            if(it != null) view0.container_bear_result.visibility = View.VISIBLE
            else view0.container_bear_result.visibility = View.GONE
        }

        dataModel.result_value_vine.observe(this as LifecycleOwner) {
            view0.tvResult_value_vine_litr.text = it
            if(it != null) view0.container_vine_result.visibility = View.VISIBLE
            else view0.container_vine_result.visibility = View.GONE
        }

        dataModel.result_value_vodka.observe(this as LifecycleOwner) {
            view0.tvResult_value_vodka_litr.text = it
            if(it != null) view0.container_vodka_result.visibility = View.VISIBLE
            else view0.container_vodka_result.visibility = View.GONE
        }

        dataModel.result_value_veget.observe(this as LifecycleOwner) {
            view0.tvResult_value_vegetable.text = it
            if(it != null) view0.container_vegetable.visibility = View.VISIBLE
            else view0.container_vegetable.visibility = View.GONE
        }

        dataModel.result_value_bread.observe(this as LifecycleOwner) {
            view0.tvResult_value_bread.text = it
            if(it != null) view0.container_bread.visibility = View.VISIBLE
            else view0.container_bread.visibility = View.GONE
        }

        return view0
    }





}
