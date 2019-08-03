package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber

import com.google.gson.annotations.SerializedName

data class BarberResponse(
    @SerializedName("help") var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: BarberResult
)