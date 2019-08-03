package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("notes") var notes: String,
    @SerializedName("type_override") var typeOverride: String,
    @SerializedName("label") var label: String
)