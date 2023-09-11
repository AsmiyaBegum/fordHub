package com.ab.fordhub.datasource.remotedata.ford_service

import com.ab.fordhub.model.ServiceDetails
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.model.VehicleService
import com.ab.fordhub.model.VehicleServiceDetail

interface FordServiceRemoteDataSource {

    suspend fun getVehicleServices(): Result<List<VehicleService>>
    fun insertServiceDetail(serviceDetail: VehicleServiceDetail)
    fun getAllServiceDetail(): List<VehicleServiceDetail>
    fun cancelService(serviceDetail: VehicleServiceDetail)
    suspend fun serviceDetails() : Result<List<ServiceDetails>>
}