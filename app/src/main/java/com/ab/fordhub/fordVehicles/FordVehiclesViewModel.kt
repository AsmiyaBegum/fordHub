package com.ab.fordhub.fordVehicles

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ab.fordhub.common.SearchWidgetState
import com.ab.fordhub.datasource.remotedata.ford_vehicle.FordTestDriveRemoteDataSourceImpl
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordnavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FordVehiclesViewModel  @Inject constructor(val navManager: NavManager) :
    ViewModel() {

    @Inject
    lateinit var fordVehicleRepository : FordTestDriveRemoteDataSourceImpl

    private val _recentVehicles = MutableLiveData<List<VehicleDetail>>()
    val recentVehicles: LiveData<List<VehicleDetail>> = _recentVehicles

    private val _testDriveVehicles = MutableLiveData<List<VehicleDetail>>()
    val testDriveVehicles: LiveData<List<VehicleDetail>> = _testDriveVehicles

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState



    private val networkScope = CoroutineScope(Dispatchers.IO)

    private val _errorDetail = MutableLiveData<Unit>()
    val errorDetail: LiveData<Unit>
        get() = _errorDetail


    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun getRecentVehicles() {
        networkScope.launch {
            try {
                val result = fordVehicleRepository.getRecentVehicleDetails()

                withContext(Dispatchers.Main) {
                    result.onSuccess { recentVehicles ->
                        _recentVehicles.value = recentVehicles
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

    fun getTestDriveVehicles() {
        networkScope.launch {
            try {
                val result = fordVehicleRepository.getTestDriveVehicleDetails()

                withContext(Dispatchers.Main) {
                    result.onSuccess { testDriveVehicles ->
                        _testDriveVehicles.value = testDriveVehicles
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

}

