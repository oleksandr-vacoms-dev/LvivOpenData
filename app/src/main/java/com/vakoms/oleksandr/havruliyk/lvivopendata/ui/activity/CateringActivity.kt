package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.CateringAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.OnItemClickListener
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.CateringViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class CateringActivity : AppCompatActivity(), OnItemClickListener {

    companion object {
        const val DATA_ID = "DATA_ID"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val records = mutableListOf<CateringRecord>()
    private lateinit var recordsAdapter: CateringAdapter
    private lateinit var viewModel: CateringViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list)

        AndroidInjection.inject(this)

        initAdapter()
        initView()
        initRecyclerView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        label.text = resources.getString(R.string.catering_label)

        back_button.setOnClickListener { finish() }
    }

    private fun initAdapter() {
        recordsAdapter = CateringAdapter(this)
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
            .get(CateringViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getCateringData()?.observe(
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

    private fun refreshRecordsAndView(newRecords: List<CateringRecord>) {
        records.addAll(newRecords)
        recordsAdapter.data = records
    }

    private fun setViewToEmpty() {
        recycler_view.visibility = View.GONE
    }

    override fun onItemClick(view: View, position: Int) {
        startDataActivityWith(records[position])
    }

    private fun startDataActivityWith(data: CateringRecord) {
        val intent = Intent(this, CateringDataActivity::class.java)
        intent.putExtra(DATA_ID, data._id)
        startActivity(intent)
    }
}