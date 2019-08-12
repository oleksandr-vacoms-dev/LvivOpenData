package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState.Companion.LOADED
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState.Companion.LOCAL
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalMarketDataStorage @Inject constructor(var database: MarketRoomDatabase) :
    LocalDataStorage<MarketRecord> {

    private var marketDao = database.marketDao()
    private val refreshState = MutableLiveData<NetworkState>()
    private val networkState = MutableLiveData<NetworkState>()

    init {
        refreshState.value = LOADED
        networkState.value = LOADED
    }

    override fun getAll(callback: DataBoundaryCallback<MarketRecord>) =
        marketDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

     fun saveAll(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAll() {
        marketDao.deleteAll()
    }

    override fun getById(id: Int) = marketDao.getById(id)

    override fun getByName(callback: DataBoundaryCallback<MarketRecord>, name: String) =
        marketDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

     fun getByName(name: String) = Listing(
        pagedList = marketDao.getByName("%$name%").toLiveData(
            config = pagedListConfig()
        ),
        refreshState = refreshState,
        networkState = networkState,
        refresh = {},
        retry = {}
    )

     fun getAll(): Listing<MarketRecord> = Listing(
        pagedList = marketDao.getAll().toLiveData(
            config = pagedListConfig()
        ),
        refreshState = refreshState,
        networkState = networkState,
        refresh = {},
        retry = {}
    )
}