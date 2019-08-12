package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class ATMItemViewHolder(
    override val containerView: View,
    private val onItemClickListener: (record: ATMRecord) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var record: ATMRecord

    init {
        containerView.setOnClickListener {
            onItemClickListener(record)
        }
    }

    fun bind(record: ATMRecord) {
        this.record = record

        label_view.text = with(record) { "$_id $bankLabel" }
        address_view.text = with(record) { address }
    }

    companion object {
        fun create(
            parent: ViewGroup, onItemClickListener: (record: ATMRecord) -> Unit
        ) = ATMItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false),
            onItemClickListener
        )
    }
}