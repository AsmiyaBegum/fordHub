package com.ab.fordhub.datasource.remotedata.ford_centers

import com.ab.fordhub.model.FordCenters
import com.ab.fordhub.model.ServiceDetails

interface FordCenterRemoteDataSource {
    suspend fun fordCenters() : Result<List<FordCenters>>
}