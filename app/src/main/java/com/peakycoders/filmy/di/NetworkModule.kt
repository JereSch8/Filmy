package com.peakycoders.filmy.di

import com.peakycoders.filmy.data.network.CastApiClient
import com.peakycoders.filmy.data.network.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val baseURL = "https://api.themoviedb.org/"
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit) : MovieApiClient {
        return retrofit.create(MovieApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideCastApiClient(retrofit: Retrofit) : CastApiClient {
        return retrofit.create(CastApiClient::class.java)
    }
}