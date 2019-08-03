package com.vakoms.oleksandr.havruliyk.lvivopendata.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.os.Build
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

fun ConnectivityManager.isConnectedToInternet(): Boolean = if (Build.VERSION.SDK_INT < 23) {
    isConnected(activeNetworkInfo)
} else {
    isConnected(getNetworkCapabilities(activeNetwork))
}


fun isConnected(network: NetworkInfo?): Boolean {
    return when (network) {
        null -> false
        else -> with(network) { isConnected && (type == TYPE_WIFI || type == TYPE_MOBILE) }
    }
}

fun isConnected(networkCapabilities: NetworkCapabilities?): Boolean {
    return when (networkCapabilities) {
        null -> false
        else -> with(networkCapabilities) { hasTransport(TRANSPORT_CELLULAR) || hasTransport(TRANSPORT_WIFI) }
    }
}
