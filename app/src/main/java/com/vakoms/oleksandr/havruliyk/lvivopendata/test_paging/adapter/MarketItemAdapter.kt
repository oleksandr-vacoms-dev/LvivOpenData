package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.NetworkStateViewHolder
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord

class MarketItemAdapter(
    private val retryCallback: () -> Unit
) : PagedListAdapter<MarketRecord, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_list -> getItem(position)?.let { (holder as MarketItemViewHolder).bind(it) }
            R.layout.network_state_item -> (holder as NetworkStateViewHolder).bind(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_list -> MarketItemViewHolder.create(
                parent
            )
            R.layout.network_state_item -> NetworkStateViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.item_list
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<MarketRecord>() {
            override fun areItemsTheSame(oldItem: MarketRecord, newItem: MarketRecord): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MarketRecord, newItem: MarketRecord): Boolean =
                oldItem == newItem
        }
    }
}