package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model

import com.google.gson.annotations.SerializedName

data class Field(
    @SerializedName("type") var type: String,
    @SerializedName("id") var id: String,
    @SerializedName("info") var info: Info
)