package com.bookApp.data.remote

import com.bookApp.data.remote.dto.BooksResponse
import retrofit2.http.GET
import com.bookApp.data.remote.dto.BookDto
import retrofit2.http.Path

interface GutendexApi {

    @GET("books")
    suspend fun getBooks(): BooksResponse

    @GET("books/{id}")
    suspend fun getBooksById(@Path("id") id: Int): BookDto

    companion object {
        const val BASE_URL = "https://gutendex.com"
    }
}