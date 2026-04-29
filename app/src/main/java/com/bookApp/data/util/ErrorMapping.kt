package com.bookApp.data.util

import com.bookApp.domain.util.DataError
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toDataError(): DataError = when (this) {
    is UnknownHostException -> DataError.Network.NO_INTERNET
    is SocketTimeoutException -> DataError.Network.REQUEST_TIMEOUT
    is SerializationException -> DataError.Network.SERIALIZATION
    is HttpException -> when (code()) {
        404 -> DataError.Book.NOT_FOUND
        408 -> DataError.Network.REQUEST_TIMEOUT
        429 -> DataError.Network.TOO_MANY_REQUESTS
        in 500..599 -> DataError.Network.SERVER_ERROR
        else -> DataError.Network.UNKNOWN
    }
    is IOException -> DataError.Network.NO_INTERNET
    else -> DataError.Network.UNKNOWN
}