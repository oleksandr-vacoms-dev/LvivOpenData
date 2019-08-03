package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness


import com.google.gson.annotations.SerializedName

data class FitnessResponse (
    @SerializedName("help")var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: FittnesResult
)