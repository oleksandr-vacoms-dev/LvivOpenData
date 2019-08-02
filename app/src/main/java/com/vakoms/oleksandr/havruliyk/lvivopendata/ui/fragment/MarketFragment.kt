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
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.MarketAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MarketViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MarketViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MarketFragment : Fragment(){

    @Inject
    lateinit var marketViewModelFactory: MarketViewModelFactory
    private lateinit var adapter: MarketAdapter

    private val recordsMutableList = mutableListOf<MarketRecord>()
    private lateinit var recyclerView: RecyclerView

    companion object {
        const val TAG = "MarketActivity"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        val view = initView(inflater.inflate(R.layout.fragment_list, container, false))

        initAdapter()
        initRecyclerView()

        connectViewModel()

        return view
    }

    private fun initView(view: View): View {
        recyclerView = view.findViewById(R.id.market_recycler_view)

        return view
    }

    private fun initAdapter(){
        adapter = this.context?.let { MarketAdapter(it, recordsMutableList) }!!
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context?.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = true
    }

    private fun connectViewModel() {
        val viewModel = ViewModelProviders.of(this, marketViewModelFactory)
            .get(MarketViewModel::class.java)

        viewModel.getMarketsData()?.observe(this,
            androidx.lifecycle.Observer
            { upDataMarketsView(it) })
    }

    private fun upDataMarketsView(markets: List<MarketRecord>) {
        recordsMutableList.addAll(markets)
        Log.i(TAG, recordsMutableList.toString())
        adapter.notifyDataSetChanged()
    }
}