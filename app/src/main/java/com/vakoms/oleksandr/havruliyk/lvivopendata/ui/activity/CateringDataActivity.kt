package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.ATMDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.CateringDataViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.label_layout.*
import javax.inject.Inject

class CateringDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CateringDataViewModel

    private var record: CateringRecord? = null
    private var recordId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list)

        AndroidInjection.inject(this)
        recordId = intent.extras?.get(CateringActivity.DATA_ID) as Int

        initView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        back_button.setOnClickListener { finish() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CateringDataViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getCateringDataById(recordId)?.observe(
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

    private fun refreshRecordAndView(newRecord: CateringRecord) {
        record = newRecord
        refreshView()
    }

    private fun refreshView() {
        label.text = record!!.name
    }

    private fun setViewToEmpty() {
        //TODO : show reference image or text(don't have data)
    }
}