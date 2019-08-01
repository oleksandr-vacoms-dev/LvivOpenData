package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "catering")
data class CateringRecord(
    @PrimaryKey
    @SerializedName("_id") val _id: Int,
    @SerializedName("district") val district: String,
    @SerializedName("street") val street: String,
    @SerializedName("building") val building: String,
    @SerializedName("name") val name: String,
    @SerializedName("seats") val seats: Double,
    @SerializedName("enterpreneur_name") val enterpreneur_name: String,
    @SerializedName("enterpreneur_name_2") val enterpreneur_name_2: String,
    @SerializedName("area") val area: Double,
    @SerializedName("code_phone_number") val code_phone_number: String,
    @SerializedName("phone_number_1") val phone_number_1: String,
    @SerializedName("phone_number_2") val phone_number_2: String,
    @SerializedName("cellphone_number_1") val cellphone_number_1: Int,
    @SerializedName("cellphone_number_2") val cellphone_number_2: String,
    @SerializedName("hours_of_work_monday") val hours_of_work_monday: String,
    @SerializedName("hours_of_work_tuesday'") val hours_of_work_tuesday: String,
    @SerializedName("hours_of_work_wednesday'") val hours_of_work_wednesday: String,
    @SerializedName("hours_of_work_thursday") val hours_of_work_thursday: String,
    @SerializedName("hours_of_work_friday") val hours_of_work_friday: String,
    @SerializedName("hours_of_work_saturday") val hours_of_work_saturday: String,
    @SerializedName("hours_of_work_sunday") val hours_of_work_sunday: String
)