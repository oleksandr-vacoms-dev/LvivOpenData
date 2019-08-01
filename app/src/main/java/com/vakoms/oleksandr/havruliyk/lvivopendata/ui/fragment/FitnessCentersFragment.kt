package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.FitnessAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.FitnessViewModel


class FitnessCentersFragment : Fragment() {

    private val recordsMutableList = mutableListOf<FitnessCentersRecord>()
    private var adapter: FitnessAdapter? = null
    private lateinit var rvHeadline: RecyclerView
    private lateinit var marketViewModel: FitnessViewModel

    companion object {
        const val TAG = "MarketActivity"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        rvHeadline = view.findViewById(R.id.market_recycler_view)

        marketViewModel = ViewModelProviders.of(this)[FitnessViewModel::class.java]
        marketViewModel.getFitnessData()?.observe(this, androidx.lifecycle.Observer {

            if (it != null) {
                recordsMutableList.addAll(it)
                Log.i(TAG, recordsMutableList.toString())
                adapter?.notifyDataSetChanged()
            } else {
                Log.i(TAG, "null")
            }
        })

        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            adapter = activity?.applicationContext?.let { FitnessAdapter(it, recordsMutableList) }
            rvHeadline.layoutManager = LinearLayoutManager(context?.applicationContext)
            rvHeadline.adapter = adapter
            rvHeadline.itemAnimator = DefaultItemAnimator()
            rvHeadline.isNestedScrollingEnabled = true
        } else {
            adapter!!.notifyDataSetChanged()
        }
    }
}