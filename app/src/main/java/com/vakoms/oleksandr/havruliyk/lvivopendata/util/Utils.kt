package com.vakoms.oleksandr.havruliyk.lvivopendata.util

import com.google.android.gms.maps.model.LatLng
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


const val DEFAULT_LATITUDE = 0.0
const val DEFAULT_LONGITUDE = 0.0

const val DATA_ID = "DATA_ID"

fun getDefaultLatLnt() = LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}