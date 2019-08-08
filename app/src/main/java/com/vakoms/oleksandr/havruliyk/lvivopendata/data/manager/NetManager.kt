package com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager

import android.content.Context
import android.net.ConnectivityManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.isConnected
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetManager @Inject constructor(var applicationContext: Context) {
    val isConnectedToInternet: Boolean?
        get() = with(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        ) {
            isConnected()
        }
}