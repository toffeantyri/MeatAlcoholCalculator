package ru.calcs.meatcalculator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import ru.calcs.meatcalculator.MainActivity
import ru.calcs.meatcalculator.OwnerInterface
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

class TopAdapter(dataModel: DataModelView) :
    RecyclerView.Adapter<TopAdapter.TopHolder>() {
    val dataModelInner = dataModel

    val list: ArrayList<String> = arrayListOf()

    inner class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.main_title)
        val edText = itemView.findViewById<TextInputEditText>(R.id.ed_ppl_count)
        val btn = itemView.findViewById<Button>(R.id.btn_get_measure)
        val column1_title = itemView.findViewById<TextView>(R.id.left_radio_title)
        val c1_r1 = itemView.findViewById<RadioButton>(R.id.l_radio_1)
        val c1_r2 = itemView.findViewById<RadioButton>(R.id.l_radio_2)
        val c1_r3 = itemView.findViewById<RadioButton>(R.id.l_radio_3)
        val c1_r4 = itemView.findViewById<RadioButton>(R.id.l_radio_4)
        val column2_title = itemView.findViewById<TextView>(R.id.right_radio_title)
        val c2_r1 = itemView.findViewById<RadioButton>(R.id.r_radio_1)
        val c2_r2 = itemView.findViewById<RadioButton>(R.id.r_radio_2)
        val c2_r3 = itemView.findViewById<RadioButton>(R.id.r_radio_3)
        val c2_r4 = itemView.findViewById<RadioButton>(R.id.r_radio_4)

        fun bindView(position: Int) {

        }

        fun bindTap(position: Int) {
            btn.setOnClickListener {
                onClickBtnTestBottomSheet()
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
        return 2
    }

    fun onClickBtnTestBottomSheet() {
        val i = dataModelInner.stateBottomSheetBehavior.value
        if (i == BottomSheetBehavior.STATE_EXPANDED) {
            dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_COLLAPSED
        } else {dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_EXPANDED}


    }


}