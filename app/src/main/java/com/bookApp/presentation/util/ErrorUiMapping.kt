package com.bookApp.presentation.util

import androidx.annotation.StringRes
import com.bookApp.R
import com.bookApp.domain.util.DataError

@StringRes
fun DataError.asStringRes(): Int = when (this) {
    DataError.Network.NO_INTERNET -> R.string.error_no_internet
    DataError.Network.REQUEST_TIMEOUT -> R.string.error_request_timeout
    DataError.Network.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
    DataError.Network.SERVER_ERROR -> R.string.error_server
    DataError.Network.SERIALIZATION -> R.string.error_serialization
    DataError.Network.UNKNOWN -> R.string.error_unknown
    DataError.Book.NOT_FOUND -> R.string.error_book_not_found
}