package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        atm.setOnClickListener { startActivity(Intent(this, ATMActivity::class.java)) }
        fitness.setOnClickListener { startActivity(Intent(this, FitnessActivity::class.java)) }
        market.setOnClickListener { startActivity(Intent(this, MarketActivity::class.java)) }
        catering.setOnClickListener { startActivity(Intent(this, CateringActivity::class.java)) }
        barber.setOnClickListener { startActivity(Intent(this, BarberActivity::class.java)) }
        map.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
    }
}
