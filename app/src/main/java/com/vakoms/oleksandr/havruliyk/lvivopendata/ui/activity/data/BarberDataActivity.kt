package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data.BarberDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_barber_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.address_view
import kotlinx.android.synthetic.main.map_button.*
import java.lang.String.format
import javax.inject.Inject

class BarberDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BarberDataViewModel

    private var record: BarberRecord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barber_data)

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
            .get(BarberDataViewModel::class.java)

        viewModel.setRecordId(intent.extras?.get(DATA_ID) as Int)
    }

    private fun initObserver() {
        viewModel.record.observe(this, Observer<BarberRecord> { record ->
            upDataView(record)
        })
    }

    private fun showOnMap() {
        if (record != null) {
            viewModel.addRecordsToMap(listOf(record!!))
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun upDataView(record: BarberRecord) {
        this.record = record
        with(record) {
            label_view.text = name
            district_view.text = district
            address_view.text = address()
            enterpreneur_name_view.text = enterpreneur_name_1
            cellphone_view.text = cellphone_number_1
            monday_view.text = monday()
            tuesday_view.text = tuesday()
            wednesday_view.text = wednesday()
            thursday_view.text = thursday()
            friday_view.text = friday()
            saturday_view.text = saturday()
            sunday_view.text = sunday()
        }
    }

    fun BarberRecord.address() = format(resources.getString(R.string.street_building), street, building)
    fun BarberRecord.monday() = format(resources.getString(R.string.monday_work_time), hours_of_work_monday)
    fun BarberRecord.tuesday() = format(resources.getString(R.string.tuesday_work_time), hours_of_work_tuesday)
    fun BarberRecord.wednesday() = format(resources.getString(R.string.wednesday_work_time), hours_of_work_wednesday)
    fun BarberRecord.thursday() = format(resources.getString(R.string.thursday_work_time), hours_of_work_thursday)
    fun BarberRecord.friday() = format(resources.getString(R.string.friday_work_time), hours_of_work_friday)
    fun BarberRecord.saturday() = format(resources.getString(R.string.saturday_work_time), hours_of_work_saturday)
    fun BarberRecord.sunday() = format(resources.getString(R.string.sunday_work_time), hours_of_work_sunday)
}