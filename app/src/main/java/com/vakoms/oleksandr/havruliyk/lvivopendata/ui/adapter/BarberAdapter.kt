package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord

class BarberAdapter(private var context: Context, private var dataList: List<BarberRecord>) :
    RecyclerView.Adapter<BarberAdapter.BarberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarberViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false)
        return BarberViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarberViewHolder, position: Int) {
        holder.name.text = dataList[position].name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class BarberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.findViewById(R.id.name_text_view)
    }
}