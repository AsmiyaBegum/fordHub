package com.ab.fordhub.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ab.fordhub.datasource.remotedata.ford_service.FordServiceRemoteDataSourceImpl
import com.ab.fordhub.model.ServiceDetails
import com.ab.fordhub.model.VehicleService
import com.ab.fordhub.model.VehicleServiceDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor( ) :
    ViewModel() {
    val fordVehicleRepository: FordServiceRemoteDataSourceImpl = FordServiceRemoteDataSourceImpl()

    private val _fordServices = MutableLiveData<List<VehicleService>>()
    val fordServices: LiveData<List<VehicleService>> = _fordServices

    private val _fordBookedServices = MutableLiveData<List<VehicleServiceDetail>>()
    val fordBookedServices: LiveData<List<VehicleServiceDetail>> = _fordBookedServices

    private val _serviceDetail = MutableLiveData<List<ServiceDetails>>()
    val serviceDetail: LiveData<List<ServiceDetails>> = _serviceDetail


    private val networkScope = CoroutineScope(Dispatchers.IO)

    private val _errorDetail = MutableLiveData<Unit>()
    val errorDetail: LiveData<Unit>
        get() = _errorDetail


    fun getVehicleServiceDetails() {
        networkScope.launch {
            try {
                val result = fordVehicleRepository.getVehicleServices()

                withContext(Dispatchers.Main) {
                    result.onSuccess { recentVehicles ->
                        _fordServices.value = recentVehicles
                    }
                    result.onFailure {
                        _errorDetail.value = Unit
                    }
                }
            } catch (e: Exception) {
                // Handle the error scenario
            }
        }
    }

    fun getBookedVehicleServiceDetails() {
        networkScope.launch {
            try {
                val result = fordVehicleRepository.getAllServiceDetail()

                withContext(Dispatchers.Main) {
                    _fordBookedServices.value = listOf(
                        VehicleServiceDetail(
                            1, "Asmiya benz",
                            "Praveen Model A1", "Wiper", "10/09/2023", "Chennai"
                        )
                    )
                }
            } catch (e: Exception) {
                // Handle the error scenario
            }
        }
    }
}

