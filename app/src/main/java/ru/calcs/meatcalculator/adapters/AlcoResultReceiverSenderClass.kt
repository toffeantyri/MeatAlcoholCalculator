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

    fun clearLastReuslt() {
        x_alco_people = 0f
        alcoIsNotEmpty = false
        allAlco_value = 0f
        bear_value = 0f
        vine_value = 0f
        vodka_value = 0f
    }

    fun setCheckAlcoIsNotEmpty() {
        alcoIsNotEmpty = when {
            allAlco_value > 0f -> true
            bear_value > 0f -> true
            vine_value > 0f -> true
            vodka_value > 0f -> true
            else -> false
        }
    }

    fun howManyAlco(radioButtonC2Num: Int): Float {
        val coefTime = when (radioButtonC2Num) {
            0 -> 1.0f    //low
            1 -> 1.9f   // medium
            2 -> 2.75f   //max
            3 -> 4.5f //over
            else -> 1.0f
        }
        return (1 * coefTime * x_alco_people)
    }
}