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
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.FitnessViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FitnessFragment : Fragment() {

    @Inject
    lateinit var fitnessViewModelFactory: FitnessViewModelFactory
    private lateinit var adapter: FitnessAdapter

    private val recordsMutableList = mutableListOf<FitnessCentersRecord>()
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
        recyclerView = view.findViewById(R.id.recycler_view)

        return view
    }

    private fun initAdapter() {
        adapter = this.context?.let { FitnessAdapter(it, recordsMutableList) }!!
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context?.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = true
    }

    private fun connectViewModel() {
        val viewModel = ViewModelProviders.of(this, fitnessViewModelFactory)
            .get(FitnessViewModel::class.java)

        viewModel.getFitnessData()?.observe(this,
            androidx.lifecycle.Observer
            { upDataFitnessView(it) })
    }

    private fun upDataFitnessView(fitnessDataList: List<FitnessCentersRecord>) {
        recordsMutableList.addAll(fitnessDataList)
        Log.i(TAG, recordsMutableList.toString())
        adapter.notifyDataSetChanged()
    }
}