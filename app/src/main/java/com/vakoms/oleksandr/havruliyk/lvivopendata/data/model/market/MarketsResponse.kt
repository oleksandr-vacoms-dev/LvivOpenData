package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market

import com.google.gson.annotations.SerializedName

data class MarketsResponse(
    @SerializedName("help") var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: MarketsResult
)