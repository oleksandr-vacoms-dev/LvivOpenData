package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data.MarketDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_market_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import java.lang.String.format
import javax.inject.Inject

class MarketDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MarketDataViewModel

    private var record: MarketRecord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_data)

        initView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        back_button.setOnClickListener { finish() }

        address_view.setOnClickListener {
            if (record != null) {
                viewModel.addRecordsToMap(listOf(record!!))
                startActivity(Intent(this, MapActivity::class.java))
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MarketDataViewModel::class.java)

        viewModel.setRecordId(intent.extras?.get(DATA_ID) as Int)
    }

    private fun initObserver() {
        viewModel.record.observe(this, Observer<MarketRecord> { record ->
            upDataView(record)
        })
    }

    private fun upDataView(record: MarketRecord) {
        this.record = record
        with(record) {
            label_view.text = name
            district_view.text = district
            address_view.text = address()
            enterpreneur_name_view.text = enterpreneurName1
            specialization_view.text = specialization
            cellphone_view.text = cellphoneNumber1
            square_view.text = storeTotalSquare
            monday_view.text = monday()
            tuesday_view.text = tuesday()
            wednesday_view.text = wednesday()
            thursday_view.text = thursday()
            friday_view.text = friday()
            saturday_view.text = saturday()
            sunday_view.text = sunday()
        }
    }

    fun MarketRecord.address() = format(resources.getString(R.string.street_building), street, building)
    fun MarketRecord.monday() = format(resources.getString(R.string.monday_work_time), hoursOfWorkMonday)
    fun MarketRecord.tuesday() = format(resources.getString(R.string.tuesday_work_time), hoursOfWorkTuesday)
    fun MarketRecord.wednesday() = format(resources.getString(R.string.wednesday_work_time), hoursOfWorkWednesday)
    fun MarketRecord.thursday() = format(resources.getString(R.string.thursday_work_time), hoursOfWorkThursday)
    fun MarketRecord.friday() = format(resources.getString(R.string.friday_work_time), hoursOfWorkFriday)
    fun MarketRecord.saturday() = format(resources.getString(R.string.saturday_work_time), hoursOfWorkSaturday)
    fun MarketRecord.sunday() = format(resources.getString(R.string.sunday_work_time), hoursOfWorkSunday)
}