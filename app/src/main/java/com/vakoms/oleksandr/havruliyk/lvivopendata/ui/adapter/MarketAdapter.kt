package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord


class MarketAdapter(private var context: Context, private var markets: ArrayList<MarketsRecord>) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false)
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.name.text = markets[position].name
    }

    override fun getItemCount(): Int {
        return markets.size
    }

    inner class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView = itemView.findViewById(R.id.name_text_view)
    }
}