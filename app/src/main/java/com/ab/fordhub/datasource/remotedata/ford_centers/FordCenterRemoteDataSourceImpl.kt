package com.ab.fordhub.datasource.remotedata.ford_centers

import com.ab.fordhub.di.AppModule
import com.ab.fordhub.model.FordCenters

class FordCenterRemoteDataSourceImpl: FordCenterRemoteDataSource {
    val apis: FordCenterAPI = AppModule.provideFordCenterAPI(
        AppModule.provideRetrofit(
            AppModule.provideOkhttpClient(AppModule.provideLoggingInterceptor())
        )
    )


    override suspend fun fordCenters(): Result<List<FordCenters>> {
        return kotlin.runCatching {
            apis.fordCenters()
        }
    }
}