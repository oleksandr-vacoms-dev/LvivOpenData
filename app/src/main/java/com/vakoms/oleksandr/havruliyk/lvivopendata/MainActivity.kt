package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.addFragmentToActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.MarketsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFitnessCenters()
    }

    private fun showMarkets(){
        addFragmentToActivity(supportFragmentManager, MarketsFragment(), R.id.content_frame)
    }

    private fun showFitnessCenters(){
        addFragmentToActivity(supportFragmentManager, MarketsFragment(), R.id.content_frame)
    }
}
