package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.coordinates

import com.google.gson.annotations.SerializedName

data class CoordinatesRecord(
    @SerializedName("_id") val _id : Int,
    @SerializedName("scode") val scode : Int,
    @SerializedName("housenumber") val housenumber : Int,
    @SerializedName("pavilion") val pavilion : String,
    @SerializedName("street_d_name") val street_d_name : String,
    @SerializedName("street_s_name") val street_s_name : String,
    @SerializedName("district_name") val district_name : String,
    @SerializedName("post_index") val post_index : Int,
    @SerializedName("change_doc_date") val change_doc_date : String,
    @SerializedName("change_doc_state") val change_doc_state : String,
    @SerializedName("x") val x : Double,
    @SerializedName("y") val y : Double
)