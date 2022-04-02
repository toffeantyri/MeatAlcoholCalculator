package ru.calcs.meatcalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.calcs.meatcalculator.R

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

    //титульник на Х человек -> X : String
    val main_titleResult_x_people : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //---------------------------------------------------------------------------------------

    //общее количество мяса
    val result_value_meat: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //количество курицы
    val result_value_chicken: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //количество свинины
    val result_value_pig: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //количество говядины
    val result_value_muu: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    //---------------------------------------------------------------------------------------
    //общее количество алкоголя
    val result_value_alco: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    // количество пива
    val result_value_bear: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    // количесто вина
    val result_value_vine: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //количество крепкого
    val result_value_vodka: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //---------------------------------------------------------------------------------------


    val result_value_bread: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val result_value_veget: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


}