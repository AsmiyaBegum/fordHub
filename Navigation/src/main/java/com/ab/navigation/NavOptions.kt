package com.ab.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Singleton
class NavManager @Inject constructor() {

    private val _routeInfo = MutableStateFlow(NavInfo())
    val routeInfo: StateFlow<NavInfo> = _routeInfo

    fun navigate(routeInfo: NavInfo?) {
        //Clear previous error, when navigating
        _routeInfo.update { routeInfo ?: NavInfo() }
    }
}