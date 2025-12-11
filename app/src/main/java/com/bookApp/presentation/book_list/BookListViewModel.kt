package com.bookApp.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookApp.domain.model.Book
import com.bookApp.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<BookListState>(BookListState.Loading)
    val state: StateFlow<BookListState> = _state.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _state.value = BookListState.Loading
            getBooksUseCase().collect { result ->
                _state.value = result.fold(
                    onSuccess = { BookListState.Success(it) },
                    onFailure = { BookListState.Error(it.message ?: "Unknown error") }
                )
            }
        }
    }
}

sealed class BookListState {
    object Loading : BookListState()
    data class Success(val books: List<Book>) : BookListState()
    data class Error(val message: String) : BookListState()
}