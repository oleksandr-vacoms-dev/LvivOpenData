package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.data.MarketDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.MarketAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.OnItemClickListener
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.MarketViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class MarketActivity : AppCompatActivity(), OnItemClickListener {

    companion object {
        const val DATA_ID = "DATA_ID"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MarketViewModel

    private val records = mutableListOf<MarketRecord>()
    private lateinit var recordsAdapter: MarketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        AndroidInjection.inject(this)

        initAdapter()
        initView()
        initRecyclerView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        label_view.text = resources.getString(R.string.market_label)

        back_button.setOnClickListener { finish() }
    }

    private fun initAdapter() {
        recordsAdapter = MarketAdapter(this)
    }

    private fun initRecyclerView() {
        with(recycler_view) {
            layoutManager = LinearLayoutManager(context?.applicationContext)
            adapter = recordsAdapter
            itemAnimator = DefaultItemAnimator()
            isNestedScrollingEnabled = true
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MarketViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getMarketData()?.observe(
            this,
            androidx.lifecycle.Observer
            {
                if (it != null) {
                    refreshRecordsAndView(it)
                } else {
                    setViewToEmpty()
                }
            })
    }

    private fun refreshRecordsAndView(newRecords: List<MarketRecord>) {
        records.addAll(newRecords)
        recordsAdapter.data = records
    }

    private fun setViewToEmpty() {
        recycler_view.visibility = View.GONE
    }

    override fun onItemClick(view: View, position: Int) {
        startDataActivityWith(records[position])
    }

    private fun startDataActivityWith(data: MarketRecord) {
        val intent = Intent(this, MarketDataActivity::class.java)
        intent.putExtra(DATA_ID, data.id)
        startActivity(intent)
    }
}