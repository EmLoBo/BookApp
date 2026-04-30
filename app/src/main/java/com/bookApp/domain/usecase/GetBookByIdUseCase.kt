package com.bookApp.domain.usecase

import com.bookApp.domain.repository.BookRepository
import com.bookApp.domain.model.Book
import com.bookApp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id: Int): Resource<Book> = repository.getBookById(id)
}