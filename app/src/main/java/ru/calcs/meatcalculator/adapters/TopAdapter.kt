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


    inner class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.main_title)
        val edLayout = itemView.findViewById<TextInputLayout>(R.id.ed_layout)
        val edText = itemView.findViewById<TextInputEditText>(R.id.ed_ppl_count)
        val btn = itemView.findViewById<Button>(R.id.btn_get_measure)
        val column1_title = itemView.findViewById<TextView>(R.id.left_radio_title)
        val radioGroup1 = itemView.findViewById<RadioGroup>(R.id.radio_gr_1)
        val c1_r1 = itemView.findViewById<RadioButton>(R.id.l_radio_1)
        val c1_r2 = itemView.findViewById<RadioButton>(R.id.l_radio_2)
        val c1_r3 = itemView.findViewById<RadioButton>(R.id.l_radio_3)
        val c1_r4 = itemView.findViewById<RadioButton>(R.id.l_radio_4)
        val column2_title = itemView.findViewById<TextView>(R.id.right_radio_title)
        val radioGroup2 = itemView.findViewById<RadioGroup>(R.id.radio_gr_2)
        val c2_r1 = itemView.findViewById<RadioButton>(R.id.r_radio_1)
        val c2_r2 = itemView.findViewById<RadioButton>(R.id.r_radio_2)
        val c2_r3 = itemView.findViewById<RadioButton>(R.id.r_radio_3)
        val c2_r4 = itemView.findViewById<RadioButton>(R.id.r_radio_4)


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
        }

        fun bindTap(position: Int) {
            btn.setOnClickListener {
                listIdColumnRadio1.addAll(arrayOf(c1_r1.id, c1_r2.id, c1_r3.id, c1_r4.id))
                listIdColumnRadio2.addAll(arrayOf(c2_r1.id, c2_r2.id, c2_r3.id, c2_r4.id))
                if (edText.text.toString() != "" && edText.text.toString() != "0") {
                    dataModelInner.result_value_meat.value = howManyMeatorAlco(
                        position,
                        edText.text.toString().toInt(),
                        radioGroup1.checkedRadioButtonId,
                        radioGroup2.checkedRadioButtonId,
                    )
                    dataModelInner.main_titleResult_x_people.value = edText.text.toString()
                    //dataModelInner.result_title_value_image.value = radioGroup1.checkedRadioButtonId
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

    fun howManyMeatorAlco(pos: Int, peopleCount: Int, toggleTitle1: Int, toggleTitle2: Int): String {
        var coefType = 1f
        var coefTime = 1f
        var countProd = ""
        if (pos == 0) {
            coefType = when (toggleTitle1) {
                listIdColumnRadio1[0] -> 1.3f   //любое
                listIdColumnRadio1[1] -> 1.3f   // кура
                listIdColumnRadio1[2] -> 1.35f    //свин
                listIdColumnRadio1[3] -> 1.4f   //говядин
                //listIdColumnRadio1[4] -> {1.2f} // рыба
                else -> 1.3f
            }

            coefTime = when (toggleTitle2) {
                listIdColumnRadio2[0] -> 1.5f    //low
                listIdColumnRadio2[1] -> 2.5f   // medium
                listIdColumnRadio2[2] -> 3.5f   //max
                listIdColumnRadio2[3] ->  4.5f //over
                else ->                    1.0f
            }
            countProd = String.format("%.2f",0.3f*peopleCount*coefType*coefTime) + " Килограмм"
        }

        if (pos == 1) {
            coefType = when (toggleTitle1) {
                listIdColumnRadio1[0] -> 1f   //любое
                listIdColumnRadio1[1] -> 1.5f   // пиво
                listIdColumnRadio1[2] -> 0.5f    //вино
                listIdColumnRadio1[3] -> 0.3f   //крепкое
                else -> 1.0f
            }

            coefTime = when (toggleTitle2) {
                listIdColumnRadio2[0] -> 1f    //low
                listIdColumnRadio2[1] -> 1.5f   // medium
                listIdColumnRadio2[2] -> 2f   //max
                listIdColumnRadio2[3] -> 3f //over
                else -> 1.0f
            }

            countProd = String.format("%.2f",(1*coefType*coefTime*peopleCount)) + " Литров"
        }
        return countProd
    }

}