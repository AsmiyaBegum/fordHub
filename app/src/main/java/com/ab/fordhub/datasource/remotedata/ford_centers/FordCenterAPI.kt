package com.ab.fordhub.datasource.remotedata.ford_centers

import com.ab.fordhub.model.FordCenters
import com.ab.fordhub.model.ServiceDetails
import retrofit2.http.GET

interface FordCenterAPI {
    @GET("v3/6836c6d1-8dca-4222-916f-1ecdb6cf58db")
    suspend fun fordCenters() : List<FordCenters>
}