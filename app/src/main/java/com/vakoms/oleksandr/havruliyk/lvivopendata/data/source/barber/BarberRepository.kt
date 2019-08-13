package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.LocalBarberDataStorage
import javax.inject.Inject

class BarberRepository @Inject constructor(
    var localDataStorage: LocalBarberDataStorage,
    var openDataApi: OpenDataApi
)