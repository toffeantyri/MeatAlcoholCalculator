package ru.calcs.meatcalculator.adapters

import android.util.Log
import ru.calcs.meatcalculator.viewmodel.DataModelView

class AlcoResultReceiverSenderClass(dataModel : DataModelView) {
    private val dataModelInner = dataModel

    var x_alco_people: Float = 0f
        set(value) {
            field = value
            dataModelInner.main_titleResult_x_people_alco.value = value
        }

    private var alcoIsNotEmpty: Boolean = false
        set(value) {
            field = value
            Log.d(ru.calcs.meatcalculator.view.TAG, "alcoIsNotEmpty $field -> $value")
            dataModelInner.alcoValueIsNoEmpty.value = value
        }

    var allAlco_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_alco.value = value
        }

    var bear_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_bear.value = value
        }

    var vine_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_vine.value = value
        }

    var vodka_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_vodka.value = value
        }

    fun clearLastReuslt(){
        x_alco_people = 0f
        alcoIsNotEmpty = false
        allAlco_value = 0f
        bear_value = 0f
        vine_value = 0f
        vodka_value = 0f
    }

    fun setCheckAlcoIsNotEmpty(){
        alcoIsNotEmpty = when {
            allAlco_value > 0f -> true
            bear_value > 0f -> true
            vine_value > 0f -> true
            vodka_value > 0f -> true
            else ->  false
        }
    }


}