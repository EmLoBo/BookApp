package com.bookApp.domain.usecase

import com.bookApp.domain.repository.BookRepository
import com.bookApp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(): Flow<Result<List<Book>>> {
        return repository.getBooks()
    }
}