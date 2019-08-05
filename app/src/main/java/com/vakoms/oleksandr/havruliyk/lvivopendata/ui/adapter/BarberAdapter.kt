package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.market_item.*

class BarberAdapter : RecyclerView.Adapter<BarberAdapter.ViewHolder>() {

    var data: MutableList<BarberRecord> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.market_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: BarberRecord) {
            name_text_view.text = with(item) { "$name $street" }
        }
    }
}