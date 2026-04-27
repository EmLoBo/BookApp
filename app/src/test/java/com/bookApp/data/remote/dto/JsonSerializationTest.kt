package com.bookApp.data.remote.dto

import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class JsonSerializationTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `AuthorDto deserializes with null birth and death years`() {
        val jsonString = """
            {
                "name": "Unknown Author",
                "birth_year": null,
                "death_year": null
            }
        """.trimIndent()

        val author = json.decodeFromString<AuthorDto>(jsonString)

        Assert.assertEquals("Unknown Author", author.name)
        Assert.assertNull(author.birthYear)
        Assert.assertNull(author.deathYear)
    }

    @Test
    fun `AuthorDto deserializes with present birth and death years`() {
        val jsonString = """
            {
                "name": "Mary Shelley",
                "birth_year": 1797,
                "death_year": 1851
            }
        """.trimIndent()

        val author = json.decodeFromString<AuthorDto>(jsonString)

        Assert.assertEquals("Mary Shelley", author.name)
        Assert.assertEquals(1797, author.birthYear)
        Assert.assertEquals(1851, author.deathYear)
    }
}