package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord

class ATMAdapter(private var context: Context, private var dataList: List<ATMRecord>) :
    RecyclerView.Adapter<ATMAdapter.PharmacyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false)
        return PharmacyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        holder.name.text = with(dataList[position]){ "$bankLabel $address" }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class PharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.findViewById(R.id.name_text_view)
    }
}