package com.bookApp.domain.usecase

import com.bookApp.domain.repository.BookRepository
import com.bookApp.domain.model.Book
import com.bookApp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): Resource<List<Book>> = repository.getBooks()

}