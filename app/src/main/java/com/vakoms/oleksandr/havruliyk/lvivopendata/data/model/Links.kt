package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model

import com.google.gson.annotations.SerializedName

data class Links (
    @SerializedName("start") var start: String,
    @SerializedName("next") var next: String
)