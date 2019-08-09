package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class MarketItemViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    override val containerView: View?
        get() = itemView

    private lateinit var record: MarketRecord

    init {
        view.setOnClickListener {

        }
    }

    fun bind(record: MarketRecord) {
        this.record = record


        label_view.text = with(record) { name }
        address_view.text = with(record) { "$street $building" }
    }

    companion object {
        fun create(parent: ViewGroup): MarketItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
            return MarketItemViewHolder(view)
        }
    }
}