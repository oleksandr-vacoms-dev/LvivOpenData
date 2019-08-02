package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering

import com.google.gson.annotations.SerializedName


data class CateringResponse (
    @SerializedName("help") var help : String,
    @SerializedName("success") var success : Boolean,
    @SerializedName("result") var result : CateringResult
)