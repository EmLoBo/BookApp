package com.bookApp.data.repository

import com.bookApp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks() : Flow<Result<List<Book>>>
    fun getBookById(id: Int) : Flow<Result<Book>>
}