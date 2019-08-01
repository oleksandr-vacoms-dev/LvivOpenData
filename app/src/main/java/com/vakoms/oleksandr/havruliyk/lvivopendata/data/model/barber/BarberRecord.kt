package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "barber")
data class BarberRecord(
    @PrimaryKey
    @SerializedName("_id") val _id: Int,
    @SerializedName("district") val district: String,
    @SerializedName("street") val street: String,
    @SerializedName("building") val building: Double,
    @SerializedName("name") val name: String,
    @SerializedName("enterpreneur_name_1") val enterpreneur_name_1: String,
    @SerializedName("enterpreneur_name_2") val enterpreneur_name_2: String,
    @SerializedName("enterpreneur_name_3") val enterpreneur_name_3: String,
    @SerializedName("enterpreneur_name_4") val enterpreneur_name_4: String,
    @SerializedName("enterpreneur_name_5") val enterpreneur_name_5: String,
    @SerializedName("code_phone_number") val code_phone_number: String,
    @SerializedName("phone_number1") val phone_number1: String,
    @SerializedName("phone_number2") val phone_number2: String,
    @SerializedName("cellphone_number_1") val cellphone_number_1: Int,
    @SerializedName("cellphone_number_2") val cellphone_number_2: Int,
    @SerializedName("cellphone_number_3") val cellphone_number_3: String,
    @SerializedName("cellphone_number_4") val cellphone_number_4: String,
    @SerializedName("hours_of_work_monday") val hours_of_work_monday: String,
    @SerializedName("hours_of_work_tuesday") val hours_of_work_tuesday: String,
    @SerializedName("hours_of_work_Wednesday") val hours_of_work_Wednesday: String,
    @SerializedName("hours_of_work_thursday") val hours_of_work_thursday: String,
    @SerializedName("hours_of_work_friday") val hours_of_work_friday: String,
    @SerializedName("hours_of_work_(saturday)") val hours_of_work_saturday: String,
    @SerializedName("hours_of_work_(sunday)") val hours_of_work_sunday: String
)