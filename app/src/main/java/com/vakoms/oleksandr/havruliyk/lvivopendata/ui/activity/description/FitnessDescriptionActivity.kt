package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.description

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.description.FitnessDescriptionViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_fitness_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import kotlinx.android.synthetic.main.map_button.*
import java.lang.String.format
import javax.inject.Inject

class FitnessDescriptionActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FitnessDescriptionViewModel

    private var record: FitnessRecord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_data)

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
            .get(FitnessDescriptionViewModel::class.java)

        viewModel.setRecordId(intent.extras?.get(DATA_ID) as Int)
    }

    private fun initObserver() {
        viewModel.record.observe(this, Observer<FitnessRecord> { record ->
            upDataView(record)
        })
    }

    private fun showOnMap() {
        if (record != null) {
            viewModel.addRecordsToMap(listOf(record!!))
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun upDataView(record: FitnessRecord) {
        this.record = record
        with(record) {
            label_view.text = name
            district_view.text = district
            address_view.text = address()
            enterpreneur_name_view.text = enterpreneurName
            cellphone_view.text = cellphoneNumber1
            square_view.text = square
            weekday_view.text = weekday()
            saturday_view.text = saturday()
            sunday_view.text = sunday()
        }
    }

    fun FitnessRecord.address() = format(resources.getString(R.string.street_building), street, building)
    fun FitnessRecord.saturday() = format(resources.getString(R.string.saturday_work_time), hoursOfWorkSaturday)
    fun FitnessRecord.sunday() = format(resources.getString(R.string.sunday_work_time), hoursOfWorkSunday)
    fun FitnessRecord.weekday() = format(resources.getString(R.string.weekday_work_time), hoursOfWorkWeekdays)
}