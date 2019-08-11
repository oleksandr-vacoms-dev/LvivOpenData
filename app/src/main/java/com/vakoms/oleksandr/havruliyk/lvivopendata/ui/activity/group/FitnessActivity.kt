package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data.FitnessDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.FitnessAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.listener.OnItemClickListener
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group.FitnessViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.hideKeyboard
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.label_view
import kotlinx.android.synthetic.main.map_button.*
import kotlinx.android.synthetic.main.search_layout.*
import javax.inject.Inject

class FitnessActivity : AppCompatActivity(),
    OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FitnessViewModel

    private var records = listOf<FitnessRecord>()
    private var cacheRecords = listOf<FitnessRecord>()
    private lateinit var recordsAdapter: FitnessAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initAdapter()
        initView()
        initSearchView()
        initRecyclerView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        label_view.text = resources.getString(R.string.fitness_label)

        back_button.setOnClickListener { finish() }
        map_button.setOnClickListener { showOnMap() }
    }

    private fun initSearchView() {
//        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                viewModel.getDataByQuery(search_view.query.toString())
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                if (newText.isNotEmpty()) {
//                    label_view.visibility = View.GONE
//                } else {
//                    label_view.visibility = View.VISIBLE
//                }
//                return true
//            }
//        })
//
//        search_view.setOnCloseListener {
//            search_view.setQuery("", false)
//            label_view.requestFocus()
//            hideKeyboard(this)
//            upDateView(cacheRecords)
//            true
//        }
    }

    private fun initAdapter() {
        recordsAdapter = FitnessAdapter(this)
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
            .get(FitnessViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.data.observe(this, Observer<List<FitnessRecord>> { records ->
            upDateView(records)
        })

        viewModel.searchData.observe(this, Observer<List<FitnessRecord>> { records ->
            upDateSearchView(records)
        })
    }

    private fun upDateView(records: List<FitnessRecord>) {
        if (records.isEmpty()) {
            showEmptyView()
        } else {
            this.records = records
            cacheRecords = records
            recordsAdapter.data = records
            showRecyclerView()
        }
    }

    private fun upDateSearchView(records: List<FitnessRecord>) {
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

    private fun showOnMap() {
        if (records.isNotEmpty()) {
            viewModel.addRecordsToMap(records)
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun startDataActivityWith(data: FitnessRecord) {
        val intent = Intent(this, FitnessDataActivity::class.java)
        intent.putExtra(DATA_ID, data.id)
        startActivity(intent)
    }
}