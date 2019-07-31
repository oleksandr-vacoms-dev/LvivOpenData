package com.vakoms.oleksandr.havruliyk.lvivopendata.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun addFragmentToActivity(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}

fun isFragmentInBackstack(
    fragmentManager: FragmentManager,
    fragmentTagName: String
): Boolean {
    for (entry in 0 until fragmentManager.backStackEntryCount) {
        if (fragmentTagName == fragmentManager.getBackStackEntryAt(entry).name) {
            return true
        }
    }
    return false
}