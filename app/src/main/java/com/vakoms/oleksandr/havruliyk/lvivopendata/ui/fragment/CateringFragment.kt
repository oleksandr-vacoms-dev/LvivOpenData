package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.adapter.CateringAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.CateringViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CateringFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val records = mutableListOf<CateringRecord>()
    private lateinit var adapter: CateringAdapter
    private lateinit var viewModel: CateringViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = initView(inflater.inflate(R.layout.fragment_list, container, false))

        AndroidSupportInjection.inject(this)
        initAdapter()
        initRecyclerView()
        initViewModel()
        initObserver()

        return view
    }

    private fun initView(view: View): View {
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    private fun initAdapter() {
        adapter = this.context?.let { CateringAdapter() }!!
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context?.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = true
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
                    refreshView(it)
                } else {
                    setViewToEmpty()
                }
            })
    }

    private fun refreshView(newRecords: List<CateringRecord>) {
        records.addAll(newRecords)
        adapter.data = records
    }

    private fun setViewToEmpty() {
        //TODO : show reference image or text(don't have data)
    }
}