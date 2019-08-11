package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map

import com.google.gson.annotations.SerializedName
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Field
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Links

data class CoordinatesResult(
    @SerializedName("include_total") var includeTotal: Boolean,
    @SerializedName("resource_id") var resourceId: String,
    @SerializedName("fields") var fields: List<Field>,
    @SerializedName("records_format") var recordsFormat: String,
    @SerializedName("pagedList") var records: List<CoordinatesRecord>,
    @SerializedName("_links") var links: Links,
    @SerializedName("total") var total: Int
)