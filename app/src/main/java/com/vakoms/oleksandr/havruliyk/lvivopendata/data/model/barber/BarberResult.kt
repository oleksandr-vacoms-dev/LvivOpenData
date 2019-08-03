package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber

import com.google.gson.annotations.SerializedName
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Field
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Links

data class BarberResult(
    @SerializedName("include_total") var include_total: Boolean,
    @SerializedName("resource_id") var resource_id: String,
    @SerializedName("fields") var fields: List<Field>,
    @SerializedName("records_format") var records_format: String,
    @SerializedName("records") var records: List<BarberRecord>,
    @SerializedName("_links") var _links: Links,
    @SerializedName("total") var total: Int
)