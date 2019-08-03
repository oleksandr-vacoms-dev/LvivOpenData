package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.ATMFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.addFragmentToActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(ATMFragment())
    }

    private fun showFragment(fragment: Fragment) {
        addFragmentToActivity(supportFragmentManager, fragment, R.id.content_frame)
    }
}
