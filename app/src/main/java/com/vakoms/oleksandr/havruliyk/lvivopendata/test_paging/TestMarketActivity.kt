package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.adapter.MarketItemAdapter
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.adapter.MarketPagingViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source.InMemoryByPageKeyRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.BASE_URL
import kotlinx.android.synthetic.main.activity_search_repository.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class TestMarketActivity : AppCompatActivity() {

    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    private lateinit var list: RecyclerView
    private lateinit var model: MarketPagingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_repository)
        list = findViewById(R.id.list)

        model = viewModel()
        initAdapter()
        initSwipeToRefresh()
    }

    private fun viewModel(): MarketPagingViewModel {
        val openDataApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenDataApi::class.java)
        val viewModelFactory = ViewModelFactory(
            InMemoryByPageKeyRepository(
                openDataApi,
                NETWORK_IO,
                applicationContext
            )
        )
        return ViewModelProviders.of(this, viewModelFactory)[MarketPagingViewModel::class.java]
    }

    private fun initAdapter() {
        val adapter = MarketItemAdapter() {
            model.retry()
        }
        list.adapter = adapter
        model.items.observe(this, Observer<PagedList<MarketRecord>> {
            adapter.submitList(it)
        })
//        model.pagedListLiveData.observe(this, Observer {
//            adapter.submitList(it)
//        })
        model.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    private fun initSwipeToRefresh() {
        model.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh.setOnRefreshListener {
            model.refresh()
        }
    }
}