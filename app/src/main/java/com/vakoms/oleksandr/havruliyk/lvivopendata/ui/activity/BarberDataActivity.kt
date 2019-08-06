package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.getAddressRecordFromBarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.BarberActivity.Companion.DATA_ID
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.BarberDataViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_barber_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
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
            val mapManager: MapManager? = MapRepository.getInstance()
            if (record != null) {
                mapManager?.addRecords(
                    getAddressRecordFromBarberRecord(
                        listOf(record!!)
                    )
                )
            }

            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BarberDataViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getBarberDataById(recordId)?.observe(
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

    private fun refreshRecordAndView(newRecord: BarberRecord) {
        record = newRecord
        refreshView()
    }

    private fun refreshView() {
        with(record!!) {
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

    private fun setViewToEmpty() {
        //TODO : show reference image or text(don't have data)
    }
}