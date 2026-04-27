package com.bookApp.di

import com.bookApp.BuildConfig
import com.bookApp.data.remote.GutendexApi
import com.bookApp.data.repository.BookRepositoryImpl
import com.bookApp.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        //if Gutendex adds a new field, app won't crash
        ignoreUnknownKeys = true
        // if JSON has null where Kotlin expects a non-null with a default, use the default. Defensive against bad backends.
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGutendexApi(client: OkHttpClient, json: Json): GutendexApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(GutendexApi.BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(GutendexApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(api: GutendexApi): BookRepository {
        return BookRepositoryImpl(api)
    }
}