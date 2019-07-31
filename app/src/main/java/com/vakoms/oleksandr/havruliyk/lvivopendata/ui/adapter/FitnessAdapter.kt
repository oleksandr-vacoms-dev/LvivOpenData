package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord


class FitnessAdapter(private var context: Context, private var fitness: ArrayList<FitnessCentersRecord>) :
    RecyclerView.Adapter<FitnessAdapter.FitnessViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false)
        return FitnessViewHolder(view)
    }

    override fun onBindViewHolder(holder: FitnessViewHolder, position: Int) {
        holder.name.text = fitness[position].name
    }

    override fun getItemCount(): Int {
        return fitness.size
    }

    inner class FitnessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView = itemView.findViewById(R.id.name_text_view)
    }
}