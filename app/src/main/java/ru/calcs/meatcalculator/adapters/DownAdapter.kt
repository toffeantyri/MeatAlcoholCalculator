package ru.calcs.meatcalculator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.calcs.meatcalculator.R
import ru.calcs.meatcalculator.viewmodel.DataModelView

class DownAdapter(dataModelView: DataModelView) :
    RecyclerView.Adapter<DownAdapter.DownHolder>() {

    val list: ArrayList<String> = arrayListOf()

    inner class DownHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {

        }

        fun bindTap(position: Int) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownHolder {
        val view = LayoutInflater.from(parent.context)
        return DownHolder(view.inflate(R.layout.rc_down_shablon, parent, false))
    }

    override fun onBindViewHolder(holder: DownHolder, position: Int) {
        holder.bindTap(position)
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun getItem() {

    }

}