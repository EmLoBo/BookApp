package com.bookApp.domain.util

sealed interface DataError {
    enum class Network : DataError {
        NO_INTERNET,
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Book : DataError {
        NOT_FOUND
    }
}