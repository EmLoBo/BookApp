package com.bookApp.data.remote.dto

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class JsonSerializationTest {

    private val gson = Gson()
    @Test
    fun `test AuthorDto with null birth and death years`() {
        val jsonWithNullYears = """
        {
            "name": "Unknown Author",
            "birth_year": null,
            "death_year": null
        }
        """.trimIndent()

        try {
            val author = gson.fromJson(jsonWithNullYears, AuthorDto::class.java)

            Assert.assertNotNull("Author should not be null", author)
            Assert.assertEquals("Author name should match", "Unknown Author", author.name)
            Assert.assertNull("Birth year should be null", author.birthYear)
            Assert.assertNull("Death year should be null", author.deathYear)

            println("✅ Nullable fields handled correctly!")

        } catch (e: Exception) {
            Assert.fail("Failed to deserialize AuthorDto with null years: ${e.message}")
        }
    }






}