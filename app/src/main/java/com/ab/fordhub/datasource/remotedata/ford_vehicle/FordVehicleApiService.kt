package com.ab.fordhub.datasource.remotedata.ford_vehicle

import com.ab.fordhub.model.VehicleDetail
import retrofit2.http.GET

interface FordVehicleApiService {

    @GET("v3/23cb2f3d-da7a-4017-8129-8d63083f35ed")
    suspend fun getRecentVehicles(): List<VehicleDetail>

    @GET("v3/23cb2f3d-da7a-4017-8129-8d63083f35ed")
    suspend fun getTestDriveVehicles(): List<VehicleDetail>
}