package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord


class MarketAdapter(private var context: Context, private var dataList: List<MarketRecord>) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false)
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.name.text = dataList[position].name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView = itemView.findViewById(R.id.name_text_view)
    }
}