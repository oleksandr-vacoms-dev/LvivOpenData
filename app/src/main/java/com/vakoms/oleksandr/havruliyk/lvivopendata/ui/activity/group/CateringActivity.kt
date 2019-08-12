package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data.CateringDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.CateringAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.listener.OnItemClickListener
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group.CateringViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.hideKeyboard
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.label_view
import kotlinx.android.synthetic.main.map_button.*
import kotlinx.android.synthetic.main.search_layout.*
import javax.inject.Inject

class CateringActivity : AppCompatActivity(), OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CateringViewModel

    private var records = listOf<CateringRecord>()

    private lateinit var pagedListAdapter: CateringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initViewModel()
        initAdapter()
        initSwipeToRefresh()
        initView()
        initSearchView()

        showAllData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CateringViewModel::class.java)
    }

    private fun initAdapter() {
        pagedListAdapter = CateringAdapter(
            retryCallback = { viewModel.retry() },
            onItemClickListener = { record -> startDataActivityWith(record) })

        recycler_view.adapter = pagedListAdapter
    }

    private fun initSwipeToRefresh() {
        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initView() {
        label_view.text = resources.getString(R.string.catering_label)

        back_button.setOnClickListener { finish() }
        map_button.setOnClickListener { showOnMap() }
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showSearchData(search_view.query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    label_view.visibility = View.INVISIBLE
                } else {
                    label_view.visibility = View.VISIBLE
                }
                return true
            }
        })

        search_view.setOnCloseListener {
            closeSearchView()
            showAllData()
            true
        }
    }

    private fun showAllData() {
        resetAdapter()
        viewModel.getAllData()
        removeObserversSearchData()
        addObserverAllData()
    }

    private fun showSearchData(searchQuery: String) {
        resetAdapter()
        viewModel.getDataByQuery(searchQuery)
        removeObserversAllData()
        addObserversSearchData()
    }

    private fun addObserversSearchData() {
        viewModel.searchPagedList.observe(this, Observer {
            pagedListAdapter.submitList(it)
            records = it
        })

        viewModel.searchRefreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })

        viewModel.searchNetworkState.observe(this, Observer {
            pagedListAdapter.setNetworkState(it)
        })
    }

    private fun addObserverAllData() {
        viewModel.pagedList.observe(this, Observer {
            pagedListAdapter.submitList(it)
            records = it
        })

        viewModel.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })

        viewModel.networkState.observe(this, Observer {
            pagedListAdapter.setNetworkState(it)
        })
    }

    private fun removeObserversAllData() {
        viewModel.pagedList.removeObservers(this)
        viewModel.refreshState.removeObservers(this)
        viewModel.networkState.removeObservers(this)
    }

    private fun removeObserversSearchData() {
        viewModel.searchPagedList.removeObservers(this)
        viewModel.searchRefreshState.removeObservers(this)
        viewModel.searchNetworkState.removeObservers(this)
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

    private fun startDataActivityWith(data: CateringRecord) {
        val intent = Intent(this, CateringDataActivity::class.java)
        intent.putExtra(DATA_ID, data._id)
        startActivity(intent)
    }

    private fun resetAdapter() {
        recycler_view.scrollToPosition(0)
        pagedListAdapter.submitList(null)
    }

    private fun closeSearchView() {
        search_view.setQuery("", false)
        label_view.requestFocus()
        hideKeyboard(this)
    }
}