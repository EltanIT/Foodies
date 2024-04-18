package com.example.foodies.di

import com.example.foodies.common.Constants
import com.example.foodies.feature_foodies.data.repository.CategoriesRepositoryImpl
import com.example.foodies.feature_foodies.data.remote.CategoriesApi
import com.example.foodies.feature_foodies.domain.repository.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoriesModule {


    @Provides
    @Singleton
    fun provideCategoriesApi(): CategoriesApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoriesApi::class.java)


    @Provides
    @Singleton
    fun provideCategoriesRepository(
        api: CategoriesApi
    ): CategoriesRepository =
        CategoriesRepositoryImpl(api)


}