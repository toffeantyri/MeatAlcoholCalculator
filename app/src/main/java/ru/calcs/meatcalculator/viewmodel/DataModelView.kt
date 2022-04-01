package ru.calcs.meatcalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.calcs.meatcalculator.R

class DataModelView : ViewModel() {

    val stateBottomSheetBehavior: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    init {
        stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_HIDDEN
    }

    val main_titleResult_x_people : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val result_title_meat: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val result_title_value_image: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val result_value_meat: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val result_title_bread: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val result_title_bread_image: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        result_title_bread_image.value = R.drawable.ic_bread
    }

    val result_value_bread: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val result_title_alco: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val result_value_alco: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }



    val result_title_veget: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val result_value_veget: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val result_title_veget_image: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        result_title_veget_image.value = R.drawable.ic_bread
    }


}