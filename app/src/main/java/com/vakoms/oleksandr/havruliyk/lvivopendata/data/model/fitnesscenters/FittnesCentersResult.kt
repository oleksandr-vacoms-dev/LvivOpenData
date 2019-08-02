package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters

import com.google.gson.annotations.SerializedName
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Field
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Links

data class FittnesCentersResult(
    @SerializedName("include_total") var includeTotal: Boolean,
    @SerializedName("resource_id") var resourceId: String,
    @SerializedName("fields") var fields: List<Field>,
    @SerializedName("records_format") var recordsFormat: String,
    @SerializedName("records") var records: List<FitnessCentersRecord>,
    @SerializedName("_links") var links: Links,
    @SerializedName("total") var total: Int
)