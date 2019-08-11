package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class MarketItemViewHolder(
    override val containerView: View,
    private val onItemClickListener: (record: MarketRecord) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var record: MarketRecord

    init {
        containerView.setOnClickListener {
            onItemClickListener(record)
        }
    }

    fun bind(record: MarketRecord) {
        this.record = record

        label_view.text = with(record) { "$id $name" }
        address_view.text = with(record) { "$street $building" }
    }

    companion object {
        fun create(
            parent: ViewGroup, onItemClickListener: (record: MarketRecord) -> Unit
        ) = MarketItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false),
            onItemClickListener
        )
    }
}