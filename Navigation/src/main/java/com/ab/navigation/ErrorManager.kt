package com.ab.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * To maintin the global error at Main Activity
 */
@Singleton
class ErrorManager @Inject constructor() {
    private val _errorInfo = MutableStateFlow(ErrorInfo())
    val errorInfo: StateFlow<ErrorInfo> = _errorInfo

    fun post(errorInfo: ErrorInfo?) {
        _errorInfo.update { errorInfo ?: ErrorInfo() }
    }
}