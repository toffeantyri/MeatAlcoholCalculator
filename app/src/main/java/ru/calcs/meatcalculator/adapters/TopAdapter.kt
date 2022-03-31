package ru.calcs.meatcalculator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.calcs.meatcalculator.MainActivity
import ru.calcs.meatcalculator.OwnerInterface
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

class TopAdapter(dataModel: DataModelView) :
    RecyclerView.Adapter<TopAdapter.TopHolder>() {
    val dataModelInner = dataModel

    val list: ArrayList<String> = arrayListOf()

    inner class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val edText = itemView.findViewById<EditText>(R.id.et_test)
        val btn = itemView.findViewById<Button>(R.id.btn_test_get)

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
        return 5
    }

    fun onClickBtnTestBottomSheet() {
        val i = dataModelInner.stateBottomSheetBehavior.value
        if (i == BottomSheetBehavior.STATE_EXPANDED) {
            dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_COLLAPSED
        } else {dataModelInner.stateBottomSheetBehavior.value = BottomSheetBehavior.STATE_EXPANDED}


    }


}