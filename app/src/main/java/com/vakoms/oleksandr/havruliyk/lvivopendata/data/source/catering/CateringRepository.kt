package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import javax.inject.Inject

class CateringRepository @Inject constructor(
    var localDataStorage: LocalCateringDataStorage,
    var openDataApi: OpenDataApi
) {
}