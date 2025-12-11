package com.bookApp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthorDto (
    val name: String,
    @SerializedName("birth_year")
    val birthYear: Int?,
    @SerializedName("death_year")
    val deathYear: Int?
)


