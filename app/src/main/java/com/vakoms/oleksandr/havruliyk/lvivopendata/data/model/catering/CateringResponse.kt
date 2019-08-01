package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering

import com.google.gson.annotations.SerializedName


data class CateringResponse (
    @SerializedName("help") val help : String,
    @SerializedName("success") val success : Boolean,
    @SerializedName("result") val result : CateringResult
)