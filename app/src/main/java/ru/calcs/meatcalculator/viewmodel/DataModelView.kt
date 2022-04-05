package ru.calcs.meatcalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DataModelView : ViewModel() {
    // БоттомШит состояние
    val stateBottomSheetBehavior: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    init {
        stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_HIDDEN
    }
    // позиция Рециклера
    val positionRC: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    init{
        positionRC.value = -1
    }

    //титульник на Х человек мясо -> X : String
    val main_titleResult_x_people_meat : MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    //титульник на Х человек алко -> X : String
    val main_titleResult_x_people_alco : MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    //------------------------------------------------------------------

    //общее количество мяса
    val result_value_meat: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_meat.value = null
    }
    //количество курицы
    val result_value_chicken: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_chicken.value = null
    }
    //количество свинины
    val result_value_pig: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_chicken.value = null
    }
    //количество говядины
    val result_value_muu: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_muu.value = null
    }

    //---------------------------------------------------------------------------------------
    //общее количество алкоголя
    val result_value_alco: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_alco.value = null
    }
    // количество пива
    val result_value_bear: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_bear.value = null
    }
    // количесто вина
    val result_value_vine: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_vine.value = null
    }
    //количество крепкого
    val result_value_vodka: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_vodka.value = null
    }
    //---------------------------------------------------------------------------------------


    val result_value_bread: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_bread.value = null
    }
    val result_value_veget: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    init{
        result_value_veget.value = null
    }


}