package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy

import com.google.gson.annotations.SerializedName
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Field
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Links

data class PharmacyResult(
    @SerializedName("include_total") val include_total: Boolean,
    @SerializedName("resource_id") val resource_id: String,
    @SerializedName("fields") val fields: List<Field>,
    @SerializedName("records_format") val records_format: String,
    @SerializedName("records") val records: List<PharmacyRecord>,
    @SerializedName("_links") val _links: Links,
    @SerializedName("total") val total: Int
)