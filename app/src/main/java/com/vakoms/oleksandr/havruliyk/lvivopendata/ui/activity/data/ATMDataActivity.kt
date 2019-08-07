package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data.ATMDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.DATA_ID
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_atm_data.*
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.address_view
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
            if (record != null) {
                viewModel.addRecordsToMap(listOf(record!!))
                startActivity(Intent(this, MapActivity::class.java))
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ATMDataViewModel::class.java)

        viewModel.setRecordId(intent.extras?.get(DATA_ID) as Int)
    }

    private fun initObserver() {
        viewModel.record.observe(this, Observer<ATMRecord> { record ->
            upDataView(record)
        })
    }

    private fun upDataView(record: ATMRecord) {
        this.record = record
        with(record) {
            label_view.text = bankLabel
            address_view.text = address
            work_time.text = workTime
        }
    }
}