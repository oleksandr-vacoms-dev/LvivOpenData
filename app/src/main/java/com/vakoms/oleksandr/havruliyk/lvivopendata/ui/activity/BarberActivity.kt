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
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.BarberAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.OnItemClickListener
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.BarberViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class BarberActivity : AppCompatActivity(), OnItemClickListener {

    companion object {
        const val DATA_ID = "DATA_ID"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val records = mutableListOf<BarberRecord>()
    private lateinit var recordsAdapter: BarberAdapter
    private lateinit var viewModel: BarberViewModel

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
        label.text = resources.getString(R.string.barber_label)

        back_button.setOnClickListener { finish() }
    }

    private fun initAdapter() {
        recordsAdapter = BarberAdapter(this)
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
            .get(BarberViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getBarberData()?.observe(
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

    private fun refreshRecordsAndView(newRecords: List<BarberRecord>) {
        records.addAll(newRecords)
        recordsAdapter.data = records
    }

    private fun setViewToEmpty() {
        recycler_view.visibility = View.GONE
    }

    override fun onItemClick(view: View, position: Int) {
        startDataActivityWith(records[position])
    }

    private fun startDataActivityWith(data: BarberRecord) {
        val intent = Intent(this, BarberDataActivity::class.java)
        intent.putExtra(DATA_ID, data._id)
        startActivity(intent)
    }
}