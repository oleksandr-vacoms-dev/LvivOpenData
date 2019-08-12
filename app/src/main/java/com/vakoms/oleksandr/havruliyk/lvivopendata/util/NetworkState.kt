package com.vakoms.oleksandr.havruliyk.lvivopendata.util

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    UNKNOWN,
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null
) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        val LOCAL = NetworkState(Status.UNKNOWN)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}