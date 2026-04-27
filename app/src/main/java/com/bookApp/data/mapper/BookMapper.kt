package com.bookApp.data.mapper

import com.bookApp.data.remote.dto.AuthorDto
import com.bookApp.data.remote.dto.BookDto
import com.bookApp.domain.model.Author
import com.bookApp.domain.model.Book

fun BookDto.toBook() : Book {
    return Book(
        id = id,
        title = title.trim(),
        authors = authors.map { it.toAuthor() },
        subjects = subjects
    )
}

fun AuthorDto.toAuthor(): Author {
return Author(
    name = name,
    birthYear = birthYear,
    deathYear = deathYear
)
}