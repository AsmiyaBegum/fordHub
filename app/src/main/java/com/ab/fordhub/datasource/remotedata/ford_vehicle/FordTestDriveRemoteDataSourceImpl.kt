package com.ab.fordhub.datasource.remotedata.ford_vehicle

import com.ab.fordhub.model.VehicleDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FordTestDriveRemoteDataSourceImpl @Inject constructor(val apis: FordVehicleApiService) {
    suspend fun getRecentVehicleDetails(): Result<List<VehicleDetail>> {
        return kotlin.runCatching {
            apis.getRecentVehicles()
        }
    }

    suspend fun getTestDriveVehicleDetails(): Result<List<VehicleDetail>> {
        return kotlin.runCatching {
            apis.getTestDriveVehicles()
        }
    }


}
