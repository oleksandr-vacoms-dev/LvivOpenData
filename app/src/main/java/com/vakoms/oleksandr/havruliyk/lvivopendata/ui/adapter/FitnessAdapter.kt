package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.viewholder.FitnessItemViewHolder
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.viewholder.NetworkStateViewHolder
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.Status

class FitnessAdapter(
    private val retryCallback: () -> Unit,
    private val onItemClickListener: (record: FitnessRecord) -> Unit
) : PagedListAdapter<FitnessRecord, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {

    private var networkState: NetworkState? = null
    private var networkItemCount = 0
    private val hasNetworkItem
        get() = networkItemCount == 1
    private val lastItemPosition
        get() = itemCount - 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_list -> getItem(position)?.let { (holder as FitnessItemViewHolder).bind(it) }
            R.layout.network_state_item -> (holder as NetworkStateViewHolder).bind(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_list -> FitnessItemViewHolder.create(parent, onItemClickListener)
            R.layout.network_state_item -> NetworkStateViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasNetworkItem && position == lastItemPosition) {
            R.layout.network_state_item
        } else {
            R.layout.item_list
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + networkItemCount
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        networkState = newNetworkState

        when (newNetworkState) {
            null -> removeNetworkItem()
            else -> when (newNetworkState.status) {
                Status.SUCCESS -> removeNetworkItem()
                Status.RUNNING -> insertNetworkItem()
                Status.FAILED -> insertNetworkItem()
            }
        }
    }

    private fun removeNetworkItem() {
        if (hasNetworkItem) {
            networkItemCount--
            notifyItemRemoved(super.getItemCount())
        }
    }

    private fun insertNetworkItem() {
        if (!hasNetworkItem) {
            networkItemCount++
            notifyItemInserted(super.getItemCount())
        } else {
            notifyItemChanged(lastItemPosition)
        }
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<FitnessRecord>() {
            override fun areItemsTheSame(oldItem: FitnessRecord, newItem: FitnessRecord) =
                (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: FitnessRecord, newItem: FitnessRecord) =
                (oldItem == newItem)
        }
    }
}