package com.bookApp.presentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookApp.domain.model.Book
import com.bookApp.domain.usecase.GetBookByIdUseCase
import com.bookApp.domain.util.DataError
import com.bookApp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(BookDetailUiState())
    private val bookId: Int = checkNotNull(savedStateHandle["bookId"]) {
        "bookId argument is required for BookDetailScreen"
    }
    val state: StateFlow<BookDetailUiState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("bookId")?.let { bookId ->
            loadBook()
        }
    }

    fun loadBook() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getBookByIdUseCase(bookId)) {
                is Resource.Success -> _state.update {
                    it.copy(isLoading = false, book = result.data, error = null)
                }
                is Resource.Error -> _state.update {
                    it.copy(isLoading = false, error = result.error)
                }
        }}
    }
}

data class BookDetailUiState(
    val book: Book? = null,
    val isLoading: Boolean = false,
    val error: DataError? = null
)