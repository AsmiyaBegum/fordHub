package com.ab.fordhub.ford_home

import androidx.lifecycle.ViewModel
import com.ab.fordnavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FordHomeViewModel @Inject constructor(val navManager: NavManager) : ViewModel() {

}