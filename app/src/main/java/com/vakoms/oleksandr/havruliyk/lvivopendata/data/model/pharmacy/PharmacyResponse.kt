package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy

import com.google.gson.annotations.SerializedName

data class PharmacyResponse(
    @SerializedName("help") var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: PharmacyResult
)