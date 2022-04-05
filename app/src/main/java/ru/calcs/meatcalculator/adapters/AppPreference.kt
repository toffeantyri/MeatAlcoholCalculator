package ru.calcs.meatcalculator.adapters

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

const val APP_PREFER = "APP_PREFERENCE"

class AppPreference(context: Context) {

    val context0 = context

    var data: SharedPreferences = context.getSharedPreferences(APP_PREFER, Context.MODE_PRIVATE)

    fun saveResult1(chicken: Float, pig: Float, muu: Float, bear: Float, vine: Float, vodka: Float, bread: Float, veget: Float, XPeopleMeat: Float, XPeopleAlco: Float) {
        data.edit().putFloat("chicken", chicken).apply()
        data.edit().putFloat("pig", pig).apply()
        data.edit().putFloat("muu", muu).apply()
        data.edit().putFloat("bear", bear).apply()
        data.edit().putFloat("vine", vine).apply()
        data.edit().putFloat("vodka", vodka).apply()
        data.edit().putFloat("bread", bread).apply()
        data.edit().putFloat("vegetable", veget).apply()
        data.edit().putFloat("people_count_meat", XPeopleMeat).apply()
        data.edit().putFloat("people_count_alco", XPeopleAlco).apply()
        Toast.makeText(context0, R.string.res_saved, Toast.LENGTH_SHORT).show()
    }

    fun loadResult1(): Map<String, Float>{
        val map = mutableMapOf<String, Float>()
        map.put("chicken",data.getFloat("chicken", 0f) )
        map.put("pig",data.getFloat("pig", 0f))
        map.put("muu",data.getFloat("muu", 0f))
        map.put("bear",data.getFloat("bear", 0f))
        map.put("vine",data.getFloat("vine", 0f))
        map.put("vodka",data.getFloat("vodka", 0f))
        map.put("bread",data.getFloat("bread", 0f))
        map.put("vegetable",data.getFloat("vegetable", 0f))
        map.put("people_count_meat",data.getFloat("people_count_meat", 0f))
        map.put("people_count_alco",data.getFloat("people_count_alco", 0f))

        Toast.makeText(context0, R.string.res_loaded, Toast.LENGTH_LONG).show()
        return map
    }

}