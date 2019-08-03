package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "atm")
data class ATMRecord(
    @PrimaryKey
    @SerializedName("_id") val _id: Int,
    @SerializedName("Банкомат") val bankLabel: String,
    @SerializedName("Адреса") val address: String,
    @SerializedName("Час роботи") val workTime : String
)