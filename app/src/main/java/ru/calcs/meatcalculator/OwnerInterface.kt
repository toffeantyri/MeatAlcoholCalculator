package ru.calcs.meatcalculator

import android.app.Activity
import androidx.lifecycle.LiveData
import ru.calcs.meatcalculator.viewmodel.DataModelView

interface OwnerInterface {
    val dataModel : DataModelView

}
