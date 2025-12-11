package com.bookApp.domain.usecase

import com.bookApp.domain.repository.BookRepository
import com.bookApp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(id: Int): Flow<Result<Book>> {
        return repository.getBookById(id)
    }
}