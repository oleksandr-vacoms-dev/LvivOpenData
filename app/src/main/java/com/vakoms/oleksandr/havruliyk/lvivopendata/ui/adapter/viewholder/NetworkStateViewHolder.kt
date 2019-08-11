package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.Status
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.toVisibility
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.network_state_item.*

class NetworkStateViewHolder(
    override val containerView: View,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        retry_button.setOnClickListener {
            retryCallback()
        }
    }

    fun bind(networkState: NetworkState?) {
        progress_bar.visibility =
            toVisibility(networkState?.status == Status.RUNNING)

        retry_button.visibility =
            toVisibility(networkState?.status == Status.FAILED)

        with(error_msg) {
            visibility = toVisibility(networkState?.msg != null)
            text = networkState?.msg
        }
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit) = NetworkStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false),
            retryCallback
        )
    }
}