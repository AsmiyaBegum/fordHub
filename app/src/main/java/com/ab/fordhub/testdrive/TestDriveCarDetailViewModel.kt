package com.ab.fordhub.testdrive

import androidx.lifecycle.ViewModel
import com.ab.fordnavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestDriveCarDetailViewModel @Inject constructor(val navManager: NavManager) : ViewModel() {

}