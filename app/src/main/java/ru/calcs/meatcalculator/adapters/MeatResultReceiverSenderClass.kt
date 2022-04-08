package ru.calcs.meatcalculator.adapters

import android.util.Log
import ru.calcs.meatcalculator.viewmodel.DataModelView

class MeatResultReceiverSenderClass(dataModel : DataModelView) {
    private val dataModelInner = dataModel

    var x_meat_people: Float = 0f
        set(value) {
            field = value
            dataModelInner.main_titleResult_x_people_meat.value = value
        }

    private var meatIsNotEmpty: Boolean = false
        set(value) {
            field = value
            dataModelInner.meatValueIsNoEmpty.value = value
        }

    var fish_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_fish.value = value
        }

    var chiken_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_chicken.value = value

        }

    var pig_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_pig.value = value

        }

    var muu_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_muu.value = value

        }

    var bread_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_bread.value = value
        }

    var veget_value: Float = 0f
        set(value) {
            field = value
            dataModelInner.result_value_veget.value = value
        }

    fun clearLastResult(){
        x_meat_people = 0f
        meatIsNotEmpty = false
        fish_value = 0f
        chiken_value = 0f
        pig_value = 0f
        muu_value = 0f
        bread_value = 0f
        veget_value = 0f
    }

    fun setCheckMeatIsNotEmpty(){
        meatIsNotEmpty = when {
            fish_value > 0f -> true
            chiken_value > 0f -> true
            pig_value > 0f -> true
            muu_value > 0f -> true
            else ->  false
        }
    }

    fun howManyMeat(radioButtonC2Num: Int) : Float{
        val coefTime = when (radioButtonC2Num) {
                0 -> 1.1f //low
                1 -> 1.8f    // medium
                2 -> 2.5f    //max
                3 -> 4f    //over
                else -> 1.0f
            }
           return (0.35f * x_meat_people * coefTime)
        }

    fun howManyBread() {
        val meatKGvalue : Float = when{
            fish_value > 0 -> {fish_value}
            chiken_value > 0 -> {chiken_value}
            pig_value > 0 -> {pig_value}
            muu_value > 0 -> {muu_value}
            else -> 0f
        }
        val coefOne = if (meatKGvalue / x_meat_people < 0.8f) 0.12f else 0.08f
        bread_value = (meatKGvalue / 0.3f) * coefOne
    }

    fun howManyVeget() {
        val meatKGvalue : Float = when{
            fish_value > 0 -> {fish_value}
            chiken_value > 0 -> {chiken_value}
            pig_value > 0 -> {pig_value}
            muu_value > 0 -> {muu_value}
            else -> 0f
        }
        val coefOne = if (meatKGvalue / x_meat_people < 0.8f) 0.15f else 0.1f
        veget_value = (meatKGvalue / 0.3f) * coefOne
    }


}