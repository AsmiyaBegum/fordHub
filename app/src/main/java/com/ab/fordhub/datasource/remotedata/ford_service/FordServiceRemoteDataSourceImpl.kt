package com.ab.fordhub.datasource.remotedata.ford_service

import com.ab.fordhub.FordHubApplication
import com.ab.fordhub.database.FordDatabase
import com.ab.fordhub.di.AppModule
import com.ab.fordhub.model.ServiceDetails
import com.ab.fordhub.model.VehicleService
import com.ab.fordhub.model.VehicleServiceDetail
import javax.inject.Inject

class FordServiceRemoteDataSourceImpl() : FordServiceRemoteDataSource {
    val roomDatabase = FordDatabase.getDatabase(FordHubApplication.appContext()!!)
    val apis: FordVehicleServiceAPI = AppModule.provideFordServiceAPIServices(
        AppModule.provideRetrofit(
            AppModule.provideOkhttpClient(AppModule.provideLoggingInterceptor())))


    override suspend fun getVehicleServices(): Result<List<VehicleService>> {
        return kotlin.runCatching {
            apis.getVehicleService()
        }
    }

    override fun insertServiceDetail(serviceDetail: VehicleServiceDetail) {
        roomDatabase.vehicleServiceDao().insertService(serviceDetail)
    }


    override fun getAllServiceDetail(): List<VehicleServiceDetail> {
        return roomDatabase.vehicleServiceDao().getAllServices()
    }


    override fun cancelService(serviceDetail: VehicleServiceDetail){
        //TO-DO
    }

    override suspend fun serviceDetails(): Result<List<ServiceDetails>> {
        return kotlin.runCatching {
            apis.serviceDetails()
        }
    }


}