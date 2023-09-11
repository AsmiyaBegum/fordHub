package com.ab.fordhub.testdrive

import androidx.lifecycle.ViewModel
import com.ab.fordnavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestDriveScreenViewModel @Inject constructor(var navManager: NavManager) : ViewModel() {
}