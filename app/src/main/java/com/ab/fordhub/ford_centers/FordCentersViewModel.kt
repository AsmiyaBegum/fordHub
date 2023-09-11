package com.ab.fordhub.ford_centers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ab.fordhub.datasource.remotedata.ford_centers.FordCenterRemoteDataSourceImpl
import com.ab.fordhub.model.FordCenters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FordCentersViewModel() : ViewModel() {

   val fordCenterRepository = FordCenterRemoteDataSourceImpl()

    private val _fordCenters = MutableLiveData<List<FordCenters>>()
    val fordCenters : LiveData<List<FordCenters>> = _fordCenters


    private val networkScope = CoroutineScope(Dispatchers.IO)

    private val _errorDetail = MutableLiveData<Unit>()
    val errorDetail: LiveData<Unit>
        get() = _errorDetail

    fun centerDetails(){
        networkScope.launch {
            try {
                val result = fordCenterRepository.fordCenters()

                withContext(Dispatchers.Main) {
                    result.onSuccess { fordCenters ->
                        _fordCenters.value = fordCenters
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