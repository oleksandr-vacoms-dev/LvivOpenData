package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "catering")
data class CateringRecord(
    @PrimaryKey
    @SerializedName("_id") var _id: Int,
    @SerializedName("district") var district: String,
    @SerializedName("street") var street: String,
    @SerializedName("building") var building: String,
    @SerializedName("name") var name: String,
    @SerializedName("seats") var seats: String,
    @SerializedName("enterpreneur_name") var enterpreneur_name: String,
    @SerializedName("enterpreneur_name_2") var enterpreneur_name_2: String,
    @SerializedName("area") var area: String,
    @SerializedName("code_phone_number") var code_phone_number: String,
    @SerializedName("phone_number_1") var phone_number_1: String,
    @SerializedName("phone_number_2") var phone_number_2: String,
    @SerializedName("cellphone_number_1") var cellphone_number_1: String,
    @SerializedName("cellphone_number_2") var cellphone_number_2: String,
    @SerializedName("hours_of_work_monday") var hours_of_work_monday: String,
    @SerializedName("hours_of_work_tuesday'") var hours_of_work_tuesday: String,
    @SerializedName("hours_of_work_wednesday'") var hours_of_work_wednesday: String,
    @SerializedName("hours_of_work_thursday") var hours_of_work_thursday: String,
    @SerializedName("hours_of_work_friday") var hours_of_work_friday: String,
    @SerializedName("hours_of_work_saturday") var hours_of_work_saturday: String,
    @SerializedName("hours_of_work_sunday") var hours_of_work_sunday: String
)