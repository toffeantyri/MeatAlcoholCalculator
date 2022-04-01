package ru.calcs.meatcalculator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

const val TAG = "MyLog"

class TopAdapter(dataModel: DataModelView) :
    RecyclerView.Adapter<TopAdapter.TopHolder>() {

    val dataModelInner = dataModel
    val list: ArrayList<ShablonDataList> = arrayListOf()

    val listIdColumnRadio1 : ArrayList<Int> = arrayListOf()
    val listIdColumnRadio2 : ArrayList<Int> = arrayListOf()


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
                listIdColumnRadio1.addAll(arrayOf(c1_r1.id,c1_r2.id,c1_r3.id,c1_r4.id,))
                listIdColumnRadio2.addAll(arrayOf(c2_r1.id,c2_r2.id,c2_r3.id,c2_r4.id,))
                if(edText.text.toString() != "") {
                    howManyMeat(
                        position,
                        edText.text.toString().toInt(),
                        radioGroup1.checkedRadioButtonId,
                        radioGroup2.checkedRadioButtonId,
                    )
                    onClickBtnTestBottomSheet()
                } else { edLayout.error = "Количество?" }

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
        val i = dataModelInner.stateBottomSheetBehavior.value
        if (i == BottomSheetBehavior.STATE_EXPANDED) {
            dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    fun updateAdapter(listResourseRC: ArrayList<ShablonDataList>) {
        list.clear()
        list.addAll(listResourseRC)
        Log.d(TAG, list.toString())
        notifyDataSetChanged()
    }

    fun howManyMeat(position: Int, peopleCount: Int, toggleTitle1 : Int, toggleTitle2: Int) : String {
        val coefMinus = when(toggleTitle1){




            else -> { 1 }
        }
        val valueMeat =

        return ""
    }

}