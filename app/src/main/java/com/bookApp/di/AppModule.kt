package com.bookApp.di

import com.bookApp.data.remote.GutendexApi
import com.bookApp.domain.repository.BookRepository
import com.bookApp.data.repository.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGutendexApi(client: OkHttpClient): GutendexApi {
        return Retrofit.Builder()
            .baseUrl(GutendexApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GutendexApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(api: GutendexApi): BookRepository {
        return BookRepositoryImpl(api)
    }
}