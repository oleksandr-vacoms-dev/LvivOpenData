package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fitness")
data class FitnessCentersRecord(
    @PrimaryKey
    @SerializedName("_id") var id: Int,
    @SerializedName("district") var district: String,
    @SerializedName("street") var street: String,
    @SerializedName("building") var building: String,
    @SerializedName("name") var name: String,
    @SerializedName("enterpreneur_name") var enterpreneurName: String,
    @SerializedName("square") var square: String,
    @SerializedName("code_phone_number") var codePhoneNumber: String,
    @SerializedName("phone_number_1") var phoneNumber1: String,
    @SerializedName("phone_number_2") var phoneNumber2: String,
    @SerializedName("cellphone_number_1") var cellphoneNumber1: String,
    @SerializedName("cellphone_number_2") var cellphoneNumber2: String,
    @SerializedName("cellphone_number_3") var cellphoneNumber3: String,
    @SerializedName("hours_of_work_(weekdays)") var hoursOfWorkWeekdays: String,
    @SerializedName("hours_of_work_(weekdays)_brake") var hoursOfWorkWeekdaysBrake: String,
    @SerializedName("hours_of_work_(saturday)") var hoursOfWorkSaturday: String,
    @SerializedName("hours_of_work_(sunday)") var hoursOfWorkSunday: String
)
