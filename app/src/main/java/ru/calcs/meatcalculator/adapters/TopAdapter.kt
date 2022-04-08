package ru.calcs.meatcalculator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

const val TAG = "MyLog"

class TopAdapter(dataModel: DataModelView) :
    RecyclerView.Adapter<TopAdapter.TopHolder>() {

    val dataModelInner = dataModel
    val list: ArrayList<ShablonDataList> = arrayListOf()

    val listIdColumnRadio1: ArrayList<Int> = arrayListOf()
    val listIdColumnRadio2: ArrayList<Int> = arrayListOf()


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
            edText.text?.append("1")
            switch_bread.visibility = if (position == 0) {
                View.VISIBLE
            } else View.INVISIBLE
            switch_veget.visibility = if (position == 0) {
                View.VISIBLE
            } else View.INVISIBLE
        }

        fun bindTap(position: Int) {

            btn.setOnClickListener {
                listIdColumnRadio1.addAll(arrayOf(c1_r1.id, c1_r2.id, c1_r3.id, c1_r4.id))
                listIdColumnRadio2.addAll(arrayOf(c2_r1.id, c2_r2.id, c2_r3.id, c2_r4.id))
                if (edText.text.toString() != "" && edText.text.toString() != "0") {
                    if (position == 0) {
                        val meatClass = MeatResultReceiverSenderClass(dataModelInner)
                        meatClass.clearLastResult()
                        val countPeopleMeat: Int = edText.text.toString().toInt()
                        meatClass.x_meat_people = countPeopleMeat.toFloat()

                        when (radioGroup1.checkedRadioButtonId) {
                            //рыбы
                            c1_r1.id -> {
                                meatClass.fish_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleMeat, radioGroup2.checkedRadioButtonId
                                )
                            }
                            //куры
                            c1_r2.id -> {
                                meatClass.chiken_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleMeat, radioGroup2.checkedRadioButtonId
                                )
                            }
                            //свинины
                            c1_r3.id -> {
                                meatClass.pig_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleMeat, radioGroup2.checkedRadioButtonId
                                )
                            }
                            //говяд
                            c1_r4.id -> {
                                meatClass.muu_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleMeat, radioGroup2.checkedRadioButtonId
                                )
                            }
                        }
                        if (switch_bread.visibility == View.VISIBLE && switch_bread.isChecked) meatClass.howManyBread()
                        if (switch_veget.visibility == View.VISIBLE && switch_veget.isChecked) meatClass.howManyVeget()
                        meatClass.setCheckMeatIsNotEmpty()
                    } else if (position == 1) {
                        val alcoClass = AlcoResultReceiverSenderClass(dataModelInner)
                        alcoClass.clearLastReuslt()
                        val countPeopleAlco: Int = edText.text.toString().toInt()
                        alcoClass.x_alco_people = countPeopleAlco.toFloat()

                        when (radioGroup1.checkedRadioButtonId) {
                            //общее алко
                            c1_r1.id -> {
                                alcoClass.allAlco_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleAlco, radioGroup2.checkedRadioButtonId
                                )
                            }
                            //пива
                            c1_r2.id -> {
                                alcoClass.bear_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleAlco, radioGroup2.checkedRadioButtonId
                                )
                            }
                            //вина
                            c1_r3.id -> {
                                alcoClass.vine_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleAlco, radioGroup2.checkedRadioButtonId
                                )
                            }
                            //крепкого
                            c1_r4.id -> {
                                alcoClass.vodka_value = howManyMeatorAlcoAllNoType(
                                    position, countPeopleAlco, radioGroup2.checkedRadioButtonId
                                )
                            }
                        }
                        alcoClass.setCheckAlcoIsNotEmpty()
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

    fun howManyMeatorAlcoAllNoType(pos: Int, peopleCount: Int, toggleTitle2: Int): Float {
        var coefTime: Float
        var countProd: Float = 1f
        if (pos == 0) {
            coefTime = when (toggleTitle2) {
                listIdColumnRadio2[0] -> 1.1f //low
                listIdColumnRadio2[1] -> 1.8f    // medium
                listIdColumnRadio2[2] -> 2.5f    //max
                listIdColumnRadio2[3] -> 4f    //over
                else -> 1.0f
            }
            countProd = (0.35f * peopleCount * coefTime)
        }
        if (pos == 1) {
            coefTime = when (toggleTitle2) {
                listIdColumnRadio2[0] -> 1.0f    //low
                listIdColumnRadio2[1] -> 1.9f   // medium
                listIdColumnRadio2[2] -> 2.75f   //max
                listIdColumnRadio2[3] -> 4.5f //over
                else -> 1.0f
            }
            countProd = (1 * coefTime * peopleCount)
        }
        return countProd
    }


}