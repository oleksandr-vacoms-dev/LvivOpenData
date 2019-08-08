package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data.CateringDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_catering_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import kotlinx.android.synthetic.main.map_button.*
import java.lang.String.format
import javax.inject.Inject

class CateringDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CateringDataViewModel

    private var record: CateringRecord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catering_data)

        initView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        back_button.setOnClickListener { finish() }
        map_button.setOnClickListener { showOnMap() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CateringDataViewModel::class.java)

        viewModel.setRecordId(intent.extras?.get(DATA_ID) as Int)
    }

    private fun initObserver() {
        viewModel.record.observe(this, Observer<CateringRecord> { record ->
            upDataView(record)
        })
    }

    private fun showOnMap() {
        if (record != null) {
            viewModel.addRecordsToMap(listOf(record!!))
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun upDataView(record: CateringRecord) {
        this.record = record
        with(record) {
            label_view.text = name
            district_view.text = district
            address_view.text = address()
            enterpreneur_name_view.text = enterpreneur_name
            seats_view.text = seats
            cellphone_view.text = cellphone_number_1
            square_view.text = area
            monday_view.text = monday()
            tuesday_view.text = tuesday()
            wednesday_view.text = wednesday()
            thursday_view.text = thursday()
            friday_view.text = friday()
            saturday_view.text = saturday()
            sunday_view.text = sunday()
        }
    }

    fun CateringRecord.address() = format(resources.getString(R.string.street_building), street, building)
    fun CateringRecord.monday() = format(resources.getString(R.string.monday_work_time), hours_of_work_monday)
    fun CateringRecord.tuesday() = format(resources.getString(R.string.tuesday_work_time), hours_of_work_tuesday)
    fun CateringRecord.wednesday() = format(resources.getString(R.string.wednesday_work_time), hours_of_work_wednesday)
    fun CateringRecord.thursday() = format(resources.getString(R.string.thursday_work_time), hours_of_work_thursday)
    fun CateringRecord.friday() = format(resources.getString(R.string.friday_work_time), hours_of_work_friday)
    fun CateringRecord.saturday() = format(resources.getString(R.string.saturday_work_time), hours_of_work_saturday)
    fun CateringRecord.sunday() = format(resources.getString(R.string.sunday_work_time), hours_of_work_sunday)
}