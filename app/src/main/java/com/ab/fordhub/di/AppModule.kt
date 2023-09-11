package com.ab.fordhub.di

import com.ab.fordhub.datasource.remotedata.ford_centers.FordCenterAPI
import com.ab.fordhub.datasource.remotedata.ford_service.FordVehicleServiceAPI
import com.ab.fordhub.datasource.remotedata.ford_vehicle.FordVehicleApiService
import com.ab.fordhub.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Named("customRetrofit")
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                    .newBuilder()
                    .build()

                val originalHttpUrl: HttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", Constants.BASE_URL)
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val newRequest: Request = requestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideApiServices(@Named("customRetrofit")retrofitClient: Retrofit): FordVehicleApiService {
        return retrofitClient.create(FordVehicleApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideFordServiceAPIServices(@Named("customRetrofit") retrofitClient: Retrofit): FordVehicleServiceAPI {
        return retrofitClient.create(FordVehicleServiceAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideFordCenterAPI(@Named("customRetrofit") retrofitClient: Retrofit): FordCenterAPI {
        return retrofitClient.create(FordCenterAPI::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideFordTestDriveRemoteDataSource(
//        api: FordVehicleApiService
//    ): FordTestDriveRemoteDataSource {
//        return FordTestDriveRemoteDataSourceImpl(api)
//    }


}