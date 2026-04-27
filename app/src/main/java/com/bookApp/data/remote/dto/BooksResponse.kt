package com.bookApp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val results: List<BookDto>
)
