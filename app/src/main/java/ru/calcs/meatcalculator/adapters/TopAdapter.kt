package ru.calcs.meatcalculator.adapters

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.calcs.meatcalculator.BottomSheetFragment
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

const val TAG = "MyLog"

class TopAdapter(dataModel: DataModelView) :
    RecyclerView.Adapter<TopAdapter.TopHolder>() {

    val dataModelInner = dataModel
    val list: ArrayList<ShablonDataList> = arrayListOf()

    val listIdColumnRadio1: ArrayList<Int> = arrayListOf()
    val listIdColumnRadio2: ArrayList<Int> = arrayListOf()
    var countPeople = "1"


    inner class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.main_title)
        val edLayout = itemView.findViewById<TextInputLayout>(R.id.ed_layout)
        val edText = itemView.findViewById<TextInputEditText>(R.id.ed_ppl_count)
        val btn = itemView.findViewById<Button>(R.id.btn_get_measure)
        val column1_title = itemView.findViewById<TextView>(R.id.left_radio_title)
        val radioGroup1 = itemView.findViewById<RadioGroup>(R.id.radio_gr_1)
        val c1_r1 = itemView.findViewById<MaterialRadioButton>(R.id.l_radio_1)
        val c1_r2 = itemView.findViewById<MaterialRadioButton>(R.id.l_radio_2)
        val c1_r3 = itemView.findViewById<MaterialRadioButton>(R.id.l_radio_3)
        val c1_r4 = itemView.findViewById<MaterialRadioButton>(R.id.l_radio_4)
        val column2_title = itemView.findViewById<TextView>(R.id.right_radio_title)
        val radioGroup2 = itemView.findViewById<RadioGroup>(R.id.radio_gr_2)
        val c2_r1 = itemView.findViewById<MaterialRadioButton>(R.id.r_radio_1)
        val c2_r2 = itemView.findViewById<MaterialRadioButton>(R.id.r_radio_2)
        val c2_r3 = itemView.findViewById<MaterialRadioButton>(R.id.r_radio_3)
        val c2_r4 = itemView.findViewById<MaterialRadioButton>(R.id.r_radio_4)
        val switch_bread = itemView.findViewById<SwitchMaterial>(R.id.switch_bread)
        val switch_veget = itemView.findViewById<SwitchMaterial>(R.id.switch_veget)


        fun bindView(position: Int) {
            title.text = list[position].mainTitle
            column1_title.text = list[position].column1Title
            column2_title.text = list[position].column2Title
            c1_r1.text = list[position].c1radio1
            c1_r2.text = list[position].c1radio2
            c1_r3.text = list[position].c1radio3
            c1_r4.text = list[position].c1radio4
            c1_r1.setCompoundDrawablesRelativeWithIntrinsicBounds(list[position].c1radio1Image, 0, 0, 0)
            c1_r2.setCompoundDrawablesRelativeWithIntrinsicBounds(list[position].c1radio2Image, 0, 0, 0)
            c1_r3.setCompoundDrawablesRelativeWithIntrinsicBounds(list[position].c1radio3Image, 0, 0, 0)
            c1_r4.setCompoundDrawablesRelativeWithIntrinsicBounds(list[position].c1radio4Image, 0, 0, 0)
            c2_r1.text = list[position].c2radio1
            c2_r2.text = list[position].c2radio2
            c2_r3.text = list[position].c2radio3
            c2_r4.text = list[position].c2radio4
            edText.text?.append(countPeople)
            switch_bread.visibility = if(position==0){View.VISIBLE} else View.INVISIBLE
            switch_veget.visibility = if(position==0){View.VISIBLE} else View.INVISIBLE
        }

        private var meatValue = 0f
        private var alcoValue = 0f
        fun bindTap(position: Int) {
            btn.setOnClickListener {
                clearCurrentResultFromPosition(position)
                listIdColumnRadio1.addAll(arrayOf(c1_r1.id, c1_r2.id, c1_r3.id, c1_r4.id))
                listIdColumnRadio2.addAll(arrayOf(c2_r1.id, c2_r2.id, c2_r3.id, c2_r4.id))
                countPeople = edText.text.toString()
                dataModelInner.positionRC.value = position
                if (edText.text.toString() != "" && edText.text.toString() != "0") {
                    // каждый пункт отдельно для каждой позиции
                    if(position == 0 ) {
                        dataModelInner.main_titleResult_x_people_meat.value = edText.text.toString().toFloat()
                        dataModelInner.meatValueIsNoEmpty.value = true
                        when (radioGroup1.checkedRadioButtonId) {
                            //рыбы
                            c1_r1.id -> {
                                meatValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_fish.value = meatValue
                            }
                            //куры
                            c1_r2.id -> {
                                meatValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_chicken.value = meatValue
                            }
                            //свинины
                            c1_r3.id -> {
                                meatValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_pig.value = meatValue
                            }
                            //говяд
                            c1_r4.id -> {
                                meatValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_muu.value = meatValue
                            }
                        }
                        if (switch_bread.visibility == View.VISIBLE && switch_bread.isChecked){
                            dataModelInner.result_value_bread.value = howManyBread(countPeople.toInt(), meatValue)
                        }
                        if(switch_veget.visibility == View.VISIBLE && switch_veget.isChecked){
                            dataModelInner.result_value_veget.value = howManyVeget(countPeople.toInt(), meatValue)
                        }
                    } else if (position == 1){
                        dataModelInner.main_titleResult_x_people_alco.value = edText.text.toString().toFloat()
                        dataModelInner.alcoValueIsNoEmpty.value = true
                        when (radioGroup1.checkedRadioButtonId) {
                            //общее алко
                            c1_r1.id -> {
                                alcoValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_alco.value = alcoValue
                            }
                            //пива
                            c1_r2.id -> {
                                alcoValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_bear.value = alcoValue
                            }
                            //вина
                            c1_r3.id -> {
                                alcoValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_vine.value = alcoValue
                            }
                            //крепкого
                            c1_r4.id -> {
                                alcoValue = howManyMeatorAlcoAllNoType(
                                    position,
                                    edText.text.toString().toInt(),
                                    radioGroup1.checkedRadioButtonId,
                                    radioGroup2.checkedRadioButtonId,
                                )
                                dataModelInner.result_value_vodka.value = alcoValue
                            }
                        }
                    }

                    onClickBtnTestBottomSheet()
                } else {
                    edLayout.error = "Введите количество человек"
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHolder {
        val view = LayoutInflater.from(parent.context)
        return TopHolder(view.inflate(R.layout.rc_top_shablon, parent, false))
    }

    override fun onBindViewHolder(holder: TopHolder, position: Int) {
        holder.bindTap(position)
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun onClickBtnTestBottomSheet() {
            dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_EXPANDED
    }

    fun updateAdapter(listResourseRC: ArrayList<ShablonDataList>) {
        list.clear()
        list.addAll(listResourseRC)
        Log.d(TAG, list.toString())
        notifyDataSetChanged()
    }

    fun howManyMeatorAlcoAllNoType(pos: Int, peopleCount: Int, toggleTitle1: Int, toggleTitle2: Int): Float {
        var coefTime = 1f
        var countProd = 0f
        if (pos == 0) {
                coefTime = when (toggleTitle2) {
                listIdColumnRadio2[0] -> 1.1f //low
                listIdColumnRadio2[1] -> 1.8f    // medium
                listIdColumnRadio2[2] -> 2.5f    //max
                listIdColumnRadio2[3] -> 4f    //over
                else -> 1.0f
            }
            countProd = (0.35f*peopleCount*coefTime)
        }

        if (pos == 1) {
                coefTime = when (toggleTitle2) {
                listIdColumnRadio2[0] -> 1.0f    //low
                listIdColumnRadio2[1] -> 1.9f   // medium
                listIdColumnRadio2[2] -> 2.75f   //max
                listIdColumnRadio2[3] -> 4.5f //over
                else -> 1.0f
            }

            countProd = (1*coefTime*peopleCount)
        }
        Log.d(TAG, "Количество по позиции $pos :  $countProd ")
        return countProd
    }

    fun howManyBread(peopleCount: Int,meatKGvalue: Float): Float {
        val coefOne = if(meatKGvalue/peopleCount < 0.8f) 0.12f else 0.08f
        return (meatKGvalue/0.3f)*coefOne
    }

    fun howManyVeget(peopleCount: Int,meatKGvalue: Float): Float{
        val coefOne = if(meatKGvalue/peopleCount < 0.8f) 0.15f else 0.1f
        return (meatKGvalue/0.3f)*coefOne
    }

    fun clearCurrentResultFromPosition(pos : Int){
        if(pos == 0) {
            dataModelInner.meatValueIsNoEmpty.value = false
            dataModelInner.main_titleResult_x_people_meat.value = null
            dataModelInner.result_value_fish.value = null
            dataModelInner.result_value_chicken.value = null
            dataModelInner.result_value_pig.value = null
            dataModelInner.result_value_muu.value = null
            dataModelInner.result_value_bread.value = null
            dataModelInner.result_value_veget.value = null
        } else if(pos == 1){
            dataModelInner.alcoValueIsNoEmpty.value = false
            dataModelInner.main_titleResult_x_people_alco.value = null
            dataModelInner.result_value_alco.value = null
            dataModelInner.result_value_bear.value = null
            dataModelInner.result_value_vine.value = null
            dataModelInner.result_value_vodka.value = null
        }
    }

}