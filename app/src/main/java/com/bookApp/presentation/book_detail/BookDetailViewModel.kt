package com.bookApp.presentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookApp.domain.model.Book
import com.bookApp.domain.usecase.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<BookDetailState>(BookDetailState.Loading)
    val state: StateFlow<BookDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("bookId")?.let { bookId ->
            loadBook(bookId)
        }
    }

    private fun loadBook(bookId: Int) {
        viewModelScope.launch {
            _state.value = BookDetailState.Loading
            getBookByIdUseCase(bookId).collect { result ->
                _state.value = result.fold(
                    onSuccess = { BookDetailState.Success(it) },
                    onFailure = { BookDetailState.Error(it.message ?: "Unknown error") }
                )
            }
        }
    }
}

sealed class BookDetailState {
    object Loading : BookDetailState()
    data class Success(val book: Book) : BookDetailState()
    data class Error(val message: String) : BookDetailState()
}