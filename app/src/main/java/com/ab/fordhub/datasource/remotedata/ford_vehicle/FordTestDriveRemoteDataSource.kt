package com.ab.fordhub.datasource.remotedata.ford_vehicle

import com.ab.fordhub.model.VehicleDetail

interface FordTestDriveRemoteDataSource {

    suspend fun getRecentVehicleDetails(): Result<List<VehicleDetail>>
    suspend fun getTestDriveVehicleDetails(): Result<List<VehicleDetail>>
}

