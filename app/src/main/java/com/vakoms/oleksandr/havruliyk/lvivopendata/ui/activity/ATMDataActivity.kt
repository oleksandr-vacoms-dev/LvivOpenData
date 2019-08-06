package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.getAddressRecordFromATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.ATMActivity.Companion.DATA_ID
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.ATMDataViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_atm_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class ATMDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ATMDataViewModel

    private var record: ATMRecord? = null
    private var recordId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm_data)

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
                    getAddressRecordFromATMRecord(
                        listOf(record!!)
                    )
                )
            }

            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ATMDataViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getATMDataById(recordId)?.observe(
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

    private fun refreshRecordAndView(newRecord: ATMRecord) {
        record = newRecord
        refreshView()
    }

    private fun refreshView() {
        label_view.text = record!!.bankLabel
        address_view.text = record!!.address
        work_time.text = record!!.workTime
    }

    private fun setViewToEmpty() {
        //TODO : show reference image or text(don't have data)
    }
}