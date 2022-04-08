package ru.calcs.meatcalculator.adapters

import android.util.Log
import ru.calcs.meatcalculator.viewmodel.DataModelView

//СКОЛЬКО ДЛЯ 1 ОПЬЯНЕНИЯ (типа порция на пару часов)
const val COEF_BEAR = 1.5f
const val COEF_VINE = 0.7f
const val COEF_VODKA = 0.2f

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
            bear_value = value * 0.2f
            vine_value = value * 0.25f
            vodka_value = value * 0.55f
            field = value
        }

    var bear_value: Float = 0f
        set(value) {
            field = value * COEF_BEAR
            dataModelInner.result_value_bear.value = value* COEF_BEAR
        }

    var vine_value: Float = 0f
        set(value) {
            field = value * COEF_VINE
            dataModelInner.result_value_vine.value = value * COEF_VINE
        }

    var vodka_value: Float = 0f
        set(value) {
            field = value * COEF_VODKA
            dataModelInner.result_value_vodka.value = value * COEF_VODKA
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