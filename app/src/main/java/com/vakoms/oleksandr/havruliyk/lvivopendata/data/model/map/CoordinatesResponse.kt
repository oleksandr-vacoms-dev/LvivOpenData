package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map

import com.google.gson.annotations.SerializedName

data class CoordinatesResponse (
    @SerializedName("help")var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: CoordinatesResult
)