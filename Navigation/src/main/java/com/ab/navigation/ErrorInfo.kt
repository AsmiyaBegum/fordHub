package com.ab.navigation

enum class ErrorState {
    NONE,
    NEGATIVE,
    POSITIVE,
    SERVER_FAIL
}

data class ErrorInfo(
    val message: String? = null,
    val errorState: ErrorState = ErrorState.NONE,
    val error: ErrorMessage?= null,
    val action: (() -> Unit)? = null
)