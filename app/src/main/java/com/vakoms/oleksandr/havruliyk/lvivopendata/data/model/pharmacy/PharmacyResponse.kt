package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy

import com.google.gson.annotations.SerializedName

data class PharmacyResponse(
    @SerializedName("help") val help: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("result") val result: PharmacyResult
)