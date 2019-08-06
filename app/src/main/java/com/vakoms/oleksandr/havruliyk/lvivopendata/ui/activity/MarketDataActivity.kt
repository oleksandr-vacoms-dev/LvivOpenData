package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.getAddressRecordFromMarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MarketDataViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_market_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class MarketDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MarketDataViewModel

    private var record: MarketRecord? = null
    private var recordId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_data)

        AndroidInjection.inject(this)
        recordId = intent.extras?.get(MarketActivity.DATA_ID) as Int

        initView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        back_button.setOnClickListener { finish() }

        address_view.setOnClickListener {
            val mapManager: MapManager? = MapRepository.getInstance()
            if (record != null) {
                mapManager?.addRecords(
                    getAddressRecordFromMarketRecord(
                        listOf(record!!)
                    )
                )
            }

            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MarketDataViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getMarketDataById(recordId)?.observe(
            this,
            androidx.lifecycle.Observer
            {
                if (it != null) {
                    refreshRecordAndView(it)
                } else {
                    setViewToEmpty()
                }
            })
    }

    private fun refreshRecordAndView(newRecord: MarketRecord) {
        record = newRecord
        refreshView()
    }

    private fun refreshView() {
        with(record!!) {
            label_view.text = name
            district_view.text = district
            address_view.text = "$street  $building"
            enterpreneur_name_view.text = enterpreneurName1
            specialization_view.text = specialization
            cellphone_view.text = cellphoneNumber1
            square_view.text = storeTotalSquare
            monday_view.text = "${resources.getString(R.string.monday)}  $hoursOfWorkMonday"
            tuesday_view.text = "${resources.getString(R.string.tuesday)}  $hoursOfWorkTuesday"
            wednesday_view.text = "${resources.getString(R.string.wednesday)} $hoursOfWorkWednesday"
            thursday_view.text = "${resources.getString(R.string.thursday)} $hoursOfWorkThursday"
            friday_view.text = "${resources.getString(R.string.friday)} $hoursOfWorkFriday"
            saturday_view.text = "${resources.getString(R.string.saturday)} $hoursOfWorkSaturday"
            sunday_view.text = "${resources.getString(R.string.sunday)} $hoursOfWorkSunday"
        }
    }

    private fun setViewToEmpty() {
        //TODO : show reference image or text(don't have data)
    }
}