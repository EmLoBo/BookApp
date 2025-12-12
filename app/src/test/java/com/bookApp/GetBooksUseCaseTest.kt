package com.bookApp

import com.bookApp.domain.repository.BookRepository
import com.bookApp.domain.usecase.GetBooksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GetBooksUseCaseTest {
    private lateinit var repository: BookRepository
    private lateinit var useCase: GetBooksUseCase

    @Before
    fun setUp(){
        repository = mockk()
        useCase = GetBooksUseCase(repository)
    }


    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        // Given
        val exception = Exception("Network error")
        coEvery { repository.getBooks() } returns flowOf(Result.failure(exception))

        // When
        val result = useCase().first()

        // Then
        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }
}