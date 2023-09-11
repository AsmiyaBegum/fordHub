package com.ab.fordhub.datasource.remotedata.ford_service

import com.ab.fordhub.model.ServiceDetails
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.model.VehicleService
import retrofit2.http.GET

interface FordVehicleServiceAPI {
    @GET("v3/2429d4d1-4c44-404c-b56b-b8dd1db4fc73")
    suspend fun getVehicleService(): List<VehicleService>

    @GET("v3/736d087b-3c8b-47d4-bb52-de469486134c")
    suspend fun serviceDetails() : List<ServiceDetails>

}