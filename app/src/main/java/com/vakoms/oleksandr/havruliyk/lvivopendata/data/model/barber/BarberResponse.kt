package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber

import com.google.gson.annotations.SerializedName

data class BarberResponse(
    @SerializedName("help") val help: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("result") val result: BarberResult
)