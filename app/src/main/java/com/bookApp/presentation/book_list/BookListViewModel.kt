package com.bookApp.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookApp.domain.model.Book
import com.bookApp.domain.usecase.GetBooksUseCase
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
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookListUiState())
    val state: StateFlow<BookListUiState> = _state.asStateFlow()

    init {
        loadBooks()
    }


    fun loadBooks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when(val result = getBooksUseCase()){
                is Resource.Success -> _state.update {
                    it.copy(isLoading = false, books = result.data, error = null)
                }
                is Resource.Error -> _state.update {
                    it.copy(isLoading = false, error = result.error)
                }
            }}
        }
}

data class BookListUiState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: DataError? = null
)