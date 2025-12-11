package com.bookApp.data.remote

import retrofit2.http.GET


interface GutendexApi {

    @GET("books")
    suspend fun getBooks(): BooksResponse

    companion object {
        const val BASE_URL = "https://gutendex.com"
    }
}