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
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.MarketAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MarketViewModel
import java.util.*

class MarketsFragment : Fragment() {

    private val recordsArrayList = ArrayList<MarketsRecord>()
    private var adapter: MarketAdapter? = null
    private lateinit var rvHeadline: RecyclerView
    private lateinit var marketViewModel: MarketViewModel

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

        marketViewModel = ViewModelProviders.of(this)[MarketViewModel::class.java]
        marketViewModel.getMarketsData()?.observe(this, androidx.lifecycle.Observer {
            recordsArrayList.addAll(it.result.records as ArrayList<MarketsRecord>)
            Log.i(TAG, recordsArrayList.toString())
            adapter?.notifyDataSetChanged()
        })

        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            adapter = context?.applicationContext?.let { MarketAdapter(it, recordsArrayList) }
            rvHeadline.layoutManager = LinearLayoutManager(context?.applicationContext)
            rvHeadline.adapter = adapter
            rvHeadline.itemAnimator = DefaultItemAnimator()
            rvHeadline.isNestedScrollingEnabled = true
        } else {
            adapter!!.notifyDataSetChanged()
        }
    }
}