package com.vakoms.oleksandr.havruliyk.lvivopendata.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

const val DATA_ID = "DATA_ID"

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun toVisibility(constraint: Boolean): Int {
    return if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}