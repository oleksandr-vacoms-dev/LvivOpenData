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
import javax.inject.Inject

class BarberDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BarberDataViewModel

    private var record: BarberRecord? = null
    private var recordId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barber_data)

        AndroidInjection.inject(this)
        recordId = intent.extras?.get(DATA_ID) as Int

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
            .get(BarberDataViewModel::class.java)

        viewModel.setRecordId(intent.extras?.get(DATA_ID) as Int)
    }

    private fun initObserver() {
        viewModel.record.observe(this, Observer<BarberRecord> { record ->
            upDataView(record)
        })
    }

    private fun upDataView(record: BarberRecord) {
        this.record = record
        with(record) {
            label_view.text = name
            district_view.text = district
            address_view.text = "$street  $building"
            enterpreneur_name_view.text = enterpreneur_name_1
            cellphone_view.text = cellphone_number_1
            monday_view.text = "${resources.getString(R.string.monday)}  $hours_of_work_monday"
            tuesday_view.text = "${resources.getString(R.string.tuesday)}  $hours_of_work_tuesday"
            wednesday_view.text = "${resources.getString(R.string.wednesday)} $hours_of_work_wednesday"
            thursday_view.text = "${resources.getString(R.string.thursday)} $hours_of_work_thursday"
            friday_view.text = "${resources.getString(R.string.friday)} $hours_of_work_friday"
            saturday_view.text = "${resources.getString(R.string.saturday)} $hours_of_work_saturday"
            sunday_view.text = "${resources.getString(R.string.sunday)} $hours_of_work_sunday"
        }
    }
}