package com.vakoms.oleksandr.havruliyk.lvivopendata.util

import com.google.android.gms.maps.model.LatLng
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.AddressRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord

const val DEFAULT_LATITUDE = 0.0
const val DEFAULT_LONGITUDE = 0.0

fun getDefaultLatLnt() = LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)

fun getAddressRecordFromFitnessRecord(data: List<FitnessRecord>): List<AddressRecord> {
    val addressRecords = mutableListOf<AddressRecord>()

    for (d: FitnessRecord in data) {
        addressRecords.add(
            AddressRecord(
                d.name,
                d.street,
                getBuildingNumber(d.building)
            )
        )
    }
    return addressRecords
}

fun getAddressRecordFromCateringRecord(data: List<CateringRecord>): List<AddressRecord> {
    val addressRecords = mutableListOf<AddressRecord>()

    for (d: CateringRecord in data) {
        addressRecords.add(
            AddressRecord(
                d.name,
                d.street,
                getBuildingNumber(d.building)
            )
        )
    }
    return addressRecords
}

fun getAddressRecordFromBarberRecord(data: List<BarberRecord>): List<AddressRecord> {
    val addressRecords = mutableListOf<AddressRecord>()

    for (d: BarberRecord in data) {
        addressRecords.add(
            AddressRecord(
                d.name,
                d.street,
                getBuildingNumber(d.building)
            )
        )
    }
    return addressRecords
}

fun getAddressRecordFromMarketRecord(data: List<MarketRecord>): List<AddressRecord> {
    val addressRecords = mutableListOf<AddressRecord>()

    for (d: MarketRecord in data) {
        addressRecords.add(
            AddressRecord(
                d.name,
                d.street,
                getBuildingNumber(d.building)
            )
        )
    }
    return addressRecords
}

fun getAddressRecordFromATMRecord(data: List<ATMRecord>): List<AddressRecord> {
    val addressRecords = mutableListOf<AddressRecord>()

    for (d: ATMRecord in data) {
        addressRecords.add(
            AddressRecord(
                d.bankLabel,
                getStreet(d.address),
                getBuildingNumber(
                    getBuilding(
                        d.address
                    )
                )
            )
        )
    }
    return addressRecords
}

fun getBuilding(address: String): String {
    val strList = address.split(",")[1].trim().split(" ")

    return if (strList.isNotEmpty()) {
        strList[0]
    } else {
        address
    }
}

fun getStreet(address: String): String {
    val str = address.split(",")[0].split(" ")
    return str[str.lastIndex]
}

fun getBuildingNumber(buildingNumber: String): String {
    if (buildingNumber.contains("/")) {
        return buildingNumber.split("/")[0]
    }
    if (buildingNumber.contains(".")) {
        return buildingNumber.split(".")[0]
    }
    return buildingNumber
}

fun List<CoordinatesRecord>.getLatLng(): LatLng {
    return if (isNotEmpty()) {
        with(this[0]) { LatLng(y, x) }
    } else {
        getDefaultLatLnt()
    }
}