package com.bookApp.data.repository

import com.bookApp.data.mapper.toBook
import com.bookApp.data.remote.GutendexApi
import com.bookApp.data.util.toDataError
import com.bookApp.domain.model.Book
import com.bookApp.domain.repository.BookRepository
import com.bookApp.domain.util.Resource
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: GutendexApi
) : BookRepository {

    override suspend fun getBooks(): Resource<List<Book>> {
        return try {
            val books = api.getBooks().results.map { it.toBook() }
            Resource.Success(books)
        } catch (e: Exception) {
            Resource.Error(e.toDataError())
        }
    }

    override suspend fun getBookById(id: Int): Resource<Book> {
        return try {
            val book = api.getBooksById(id).toBook()
            Resource.Success(book)
        } catch (e: Exception) {
            Resource.Error(e.toDataError())
        }
    }
}