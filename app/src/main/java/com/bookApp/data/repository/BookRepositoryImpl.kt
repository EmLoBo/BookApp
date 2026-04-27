package com.bookApp.data.repository

import com.bookApp.data.mapper.toBook
import com.bookApp.data.remote.GutendexApi
import com.bookApp.domain.model.Book
import com.bookApp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: GutendexApi
): BookRepository {

    override fun getBooks(): Flow<Result<List<Book>>> = flow {
        try {
            val response = api.getBooks()
            val books = response.results.map { it.toBook() }
            emit(Result.success(books))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getBookById(id: Int): Flow<Result<Book>> = flow {
        try {
            val book = api.getBooksById(id).toBook()
            emit(Result.success(book))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}