package com.ab.fordhub.welcome

import androidx.lifecycle.ViewModel
import com.ab.fordnavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(val navManager: NavManager) : ViewModel() {

}