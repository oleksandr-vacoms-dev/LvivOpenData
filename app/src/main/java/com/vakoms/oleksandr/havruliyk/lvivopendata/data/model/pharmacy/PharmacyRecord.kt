package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pharmacy")
data class PharmacyRecord(
    @PrimaryKey
    @SerializedName("_id") var _id: Int,
    @SerializedName("district") var district: String,
    @SerializedName("street") var street: String,
    @SerializedName("building") var building: Double,
    @SerializedName("pharmacy name") var pharmacy_name: String,
    @SerializedName("enterpreneur name") var enterpreneur_name: String,
    @SerializedName("phone number 1") var phone_number_1: Int?,
    @SerializedName("phone number 2") var phone_number_2: String?,
    @SerializedName("phone number 3") var phone_number_3: String?,
    @SerializedName("phone number 4") var phone_number_4: String?,
    @SerializedName("cellphone number 1") var cellphone_number_1: String?,
    @SerializedName("cellphone number 2") var cellphone_number_2: String?,
    @SerializedName("hours of work (weekdays)") var hours_of_work_weekdays: String,
    @SerializedName("hours of work (Saturday)") var hours_of_work_Saturday: String,
    @SerializedName("hours of work (Sunday)") var hours_of_work_Sunday: String,
    @SerializedName("e-mail 1") var email_1: String,
    @SerializedName("e-mail 2") var email_2: String
)