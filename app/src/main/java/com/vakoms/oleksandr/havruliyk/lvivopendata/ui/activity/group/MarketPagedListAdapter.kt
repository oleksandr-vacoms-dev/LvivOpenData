package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class MarketPagedListAdapter : PagedListAdapter<MarketRecord, MarketPagedListAdapter.ViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<MarketRecord>() {
            override fun areItemsTheSame(oldItem: MarketRecord, newItem: MarketRecord): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MarketRecord, newItem: MarketRecord): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    ), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: MarketRecord) {
            //itemView.setOnClickListener { onClickListener.onItemClick(itemView, position) }

            label_view.text = with(item) { name }
            address_view.text = with(item) { "$street $building" }
        }
    }
}