package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord

class CateringAdapter(private var context: Context, private var dataList: List<CateringRecord>) :
    RecyclerView.Adapter<CateringAdapter.CateringViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateringViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false)
        return CateringViewHolder(view)
    }

    override fun onBindViewHolder(holder: CateringViewHolder, position: Int) {
        holder.name.text = dataList[position].name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class CateringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.findViewById(R.id.name_text_view)
    }
}