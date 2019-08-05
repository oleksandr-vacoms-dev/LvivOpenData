package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class MarketAdapter(var onClickListener: OnItemClickListener) : RecyclerView.Adapter<MarketAdapter.ViewHolder>() {

    var data: MutableList<MarketRecord> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position], onClickListener)

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: MarketRecord, onClickListener: OnItemClickListener) {
            itemView.setOnClickListener { onClickListener.onItemClick(itemView, position) }

            label.text = with(item) { name }
            address.text = with(item) { "$street $building" }
        }
    }
}