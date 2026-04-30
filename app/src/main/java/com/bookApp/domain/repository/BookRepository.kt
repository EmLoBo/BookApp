package com.bookApp.domain.repository

import com.bookApp.domain.model.Book
import com.bookApp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBooks(): Resource<List<Book>>
    suspend fun getBookById(id: Int): Resource<Book>
}