package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm

import com.google.gson.annotations.SerializedName

data class ATMResponse(
    @SerializedName("help") var help: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("result") var result: ATMResult
)