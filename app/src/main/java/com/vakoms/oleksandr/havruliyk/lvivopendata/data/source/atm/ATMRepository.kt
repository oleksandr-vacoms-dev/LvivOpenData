package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.LocalATMDataStorage
import javax.inject.Inject

class ATMRepository @Inject constructor(
    var localDataStorage: LocalATMDataStorage,
    var openDataApi: OpenDataApi
)