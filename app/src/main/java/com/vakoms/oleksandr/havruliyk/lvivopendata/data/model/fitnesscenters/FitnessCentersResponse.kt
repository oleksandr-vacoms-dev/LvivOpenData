package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters


import com.google.gson.annotations.SerializedName
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResult

data class FitnessCentersResponse (
    @SerializedName("help")var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: FittnesCentersResult
)