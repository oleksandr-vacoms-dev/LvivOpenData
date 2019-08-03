package com.vakoms.oleksandr.havruliyk.lvivopendata.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetManager @Inject constructor(var applicationContext: Context) {
    val isConnectedToInternet: Boolean?
        get() = with(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        ) {
            isConnectedToInternet()
        }
}

fun ConnectivityManager.isConnectedToInternet() = isConnected(getNetworkCapabilities(activeNetwork))

fun isConnected(networkCapabilities: NetworkCapabilities?): Boolean {
    return when (networkCapabilities) {
        null -> false
        else -> with(networkCapabilities) { hasTransport(TRANSPORT_CELLULAR) || hasTransport(TRANSPORT_WIFI) }
    }
}
