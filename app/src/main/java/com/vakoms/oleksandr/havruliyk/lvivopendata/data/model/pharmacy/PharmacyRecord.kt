package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pharmacy")
data class PharmacyRecord(
    @PrimaryKey
    @SerializedName("_id") val _id: Int,
    @SerializedName("district") val district: String,
    @SerializedName("street") val street: String,
    @SerializedName("building") val building: Double,
    @SerializedName("pharmacy name") val pharmacy_name: String,
    @SerializedName("enterpreneur name") val enterpreneur_name: String,
    @SerializedName("phone number 1") val phone_number_1: Int,
    @SerializedName("phone number 2") val phone_number_2: String,
    @SerializedName("phone number 3") val phone_number_3: String,
    @SerializedName("phone number 4") val phone_number_4: String,
    @SerializedName("cellphone number 1") val cellphone_number_1: String,
    @SerializedName("cellphone number 2") val cellphone_number_2: String,
    @SerializedName("hours of work (weekdays)") val hours_of_work_weekdays: String,
    @SerializedName("hours of work (Saturday)") val hours_of_work_Saturday: String,
    @SerializedName("hours of work (Sunday)") val hours_of_work_Sunday: String,
    @SerializedName("e-mail 1") val email_1: String,
    @SerializedName("e-mail 2") val email_2: String
)