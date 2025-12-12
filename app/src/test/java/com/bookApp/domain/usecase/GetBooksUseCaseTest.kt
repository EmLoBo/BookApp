package com.bookApp.domain.usecase

import com.bookApp.domain.repository.BookRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetBooksUseCaseTest {
    private lateinit var repository: BookRepository
    private lateinit var useCase: GetBooksUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetBooksUseCase(repository)
    }


    @Test
    fun `invoke returns failure when repository fails`() = runTest {

        val exception = Exception("Network error")
        coEvery { repository.getBooks() } returns flowOf(Result.failure(exception))

        val result = useCase().first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals("Network error", result.exceptionOrNull()?.message)
    }
}