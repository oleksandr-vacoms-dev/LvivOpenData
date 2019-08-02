package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.FitnessFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.MarketFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.addFragmentToActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMarkets()
        //showFitness()
    }

    private fun showMarkets() {
        addFragmentToActivity(supportFragmentManager, MarketFragment(), R.id.content_frame)
    }

    private fun showFitness() {
        addFragmentToActivity(supportFragmentManager, FitnessFragment(), R.id.content_frame)
    }
}
