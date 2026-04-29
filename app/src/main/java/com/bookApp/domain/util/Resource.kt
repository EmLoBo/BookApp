package com.bookApp.domain.util


//Naming it Resource (not Result) avoids collision with kotlin.Result.
sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val error: DataError) : Resource<Nothing>
}