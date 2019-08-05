package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "barber")
data class BarberRecord(
    @PrimaryKey
    @SerializedName("_id") var _id: Int,
    @SerializedName("district") var district: String,
    @SerializedName("street") var street: String,
    @SerializedName("building") var building: String,
    @SerializedName("name") var name: String,
    @SerializedName("enterpreneur_name_1") var enterpreneur_name_1: String,
    @SerializedName("enterpreneur_name_2") var enterpreneur_name_2: String,
    @SerializedName("enterpreneur_name_3") var enterpreneur_name_3: String,
    @SerializedName("enterpreneur_name_4") var enterpreneur_name_4: String,
    @SerializedName("enterpreneur_name_5") var enterpreneur_name_5: String,
    @SerializedName("code_phone_number") var code_phone_number: String,
    @SerializedName("phone_number1") var phone_number1: String,
    @SerializedName("phone_number2") var phone_number2: String,
    @SerializedName("cellphone_number_1") var cellphone_number_1: String,
    @SerializedName("cellphone_number_2") var cellphone_number_2: String,
    @SerializedName("cellphone_number_3") var cellphone_number_3: String,
    @SerializedName("cellphone_number_4") var cellphone_number_4: String,
    @SerializedName("hours_of_work_monday") var hours_of_work_monday: String,
    @SerializedName("hours_of_work_tuesday") var hours_of_work_tuesday: String,
    @SerializedName("hours_of_work_Wednesday") var hours_of_work_wednesday: String,
    @SerializedName("hours_of_work_thursday") var hours_of_work_thursday: String,
    @SerializedName("hours_of_work_friday") var hours_of_work_friday: String,
    @SerializedName("hours_of_work_(saturday)") var hours_of_work_saturday: String,
    @SerializedName("hours_of_work_(sunday)") var hours_of_work_sunday: String
)