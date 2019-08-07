package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data.ATMDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.ATMAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.listener.OnItemClickListener
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group.ATMViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class ATMActivity : AppCompatActivity(), OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ATMViewModel

    private var records = listOf<ATMRecord>()
    private lateinit var recordsAdapter: ATMAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initAdapter()
        initView()
        initRecyclerView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        label_view.text = resources.getString(R.string.atm_label)

        back_button.setOnClickListener { finish() }
    }

    private fun initAdapter() {
        recordsAdapter = ATMAdapter(this)
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
            .get(ATMViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.data.observe(this, Observer<List<ATMRecord>> { records ->
            upDataView(records)
        })
    }

    private fun upDataView(records: List<ATMRecord>) {
        if (records.isEmpty()) {
            showEmptyView()
        } else {
            this.records = records
            recordsAdapter.data = records
            showRecyclerView()
        }
    }

    private fun showEmptyView() {
        recycler_view.visibility = View.GONE
    }

    private fun showRecyclerView() {
        recycler_view.visibility = View.VISIBLE
    }

    override fun onItemClick(view: View, position: Int) {
        startDataActivityWith(records[position])
    }

    private fun startDataActivityWith(data: ATMRecord) {
        val intent = Intent(this, ATMDataActivity::class.java)
        intent.putExtra(DATA_ID, data._id)
        startActivity(intent)
    }
}