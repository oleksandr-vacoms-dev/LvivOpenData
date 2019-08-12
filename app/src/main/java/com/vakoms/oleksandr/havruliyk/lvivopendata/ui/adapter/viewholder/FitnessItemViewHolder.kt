package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class FitnessItemViewHolder(
    override val containerView: View,
    private val onItemClickListener: (record: FitnessRecord) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var record: FitnessRecord

    init {
        containerView.setOnClickListener {
            onItemClickListener(record)
        }
    }

    fun bind(record: FitnessRecord) {
        this.record = record

        label_view.text = with(record) { "$id $name" }
        address_view.text = with(record) { "$street $building" }
    }

    companion object {
        fun create(
            parent: ViewGroup, onItemClickListener: (record: FitnessRecord) -> Unit
        ) = FitnessItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false),
            onItemClickListener
        )
    }
}