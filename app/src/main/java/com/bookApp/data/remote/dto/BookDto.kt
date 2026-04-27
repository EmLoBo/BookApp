package com.bookApp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val id: Int,
    val title: String,
    val authors: List<AuthorDto>,
    val subjects: List<String>
)
