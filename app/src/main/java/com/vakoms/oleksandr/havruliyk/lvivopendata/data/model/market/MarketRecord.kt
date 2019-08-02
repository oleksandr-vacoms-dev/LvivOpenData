package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "market")
data class MarketRecord(
    @PrimaryKey
    @SerializedName("_id") var id: Int,
    @SerializedName("district") var district: String,
    @SerializedName("street") var street: String,
    @SerializedName("building") var building: String,
    @SerializedName("name") var name: String,
    @SerializedName("specialization") var specialization: String,
    @SerializedName("enterpreneur_name_1") var enterpreneurName1: String,
    @SerializedName("enterpreneur_name_2") var enterpreneurName2: String,
    @SerializedName("enterpreneur_name_3") var enterpreneurName3: String,
    @SerializedName("store_total_square") var storeTotalSquare: String,
    @SerializedName("code_phone_number") var codePhoneNumber: String,
    @SerializedName("phone_number_1") var phoneNumber1: String,
    @SerializedName("phone_number_2") var phoneNumber2: String,
    @SerializedName("phone_number_3") var phoneNumber3: String,
    @SerializedName("phone_number_4") var phoneNumber4: String,
    @SerializedName("cellphone_number_1") var cellphoneNumber1: String,
    @SerializedName("cellphone_number_2") var cellphoneNumber2: String,
    @SerializedName("cellphone_number_3") var cellphoneNumber3: String,
    @SerializedName("hours_of_work_monday") var hoursOfWorkMonday: String,
    @SerializedName("hours_of_work_tuesday") var hoursOfWorkTuesday: String,
    @SerializedName("hours_of_work_wednesday") var hoursOfWorkWednesday: String,
    @SerializedName("hours_of_work_thursday") var hoursOfWorkThursday: String,
    @SerializedName("hours_of_work_friday") var hoursOfWorkFriday: String,
    @SerializedName("hours_of_work_brake") var hoursOfWorkBrake: String,
    @SerializedName("hours_of_work_saturday") var hoursOfWorkSaturday: String,
    @SerializedName("hours_of_work_sunday") var hoursOfWorkSunday: String
)