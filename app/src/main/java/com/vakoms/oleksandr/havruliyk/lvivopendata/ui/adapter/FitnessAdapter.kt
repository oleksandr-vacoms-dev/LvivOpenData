package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class FitnessAdapter(var onClickListener: OnItemClickListener) : RecyclerView.Adapter<FitnessAdapter.ViewHolder>() {

    var data: MutableList<FitnessRecord> = mutableListOf()
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

        fun bind(item: FitnessRecord, onClickListener: OnItemClickListener) {
            itemView.setOnClickListener { onClickListener.onItemClick(itemView, position) }

            label.text = with(item) { "$name $street" }
            address.text = with(item) { "$street $building" }
        }
    }
}