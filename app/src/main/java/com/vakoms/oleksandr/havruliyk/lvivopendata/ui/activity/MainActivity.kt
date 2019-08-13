package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        atm.setOnClickListener { startActivity(Intent(this, ATMsActivity::class.java)) }
        fitness.setOnClickListener { startActivity(Intent(this, FitnessesActivity::class.java)) }
        market.setOnClickListener { startActivity(Intent(this, MarketsActivity::class.java)) }
        catering.setOnClickListener { startActivity(Intent(this, CateringsActivity::class.java)) }
        barber.setOnClickListener { startActivity(Intent(this, BarbersActivity::class.java)) }
    }
}
