package com.example.foodies.di

import com.example.foodies.common.Constants
import com.example.foodies.feature_foodies.data.remote.ProductsApi
import com.example.foodies.feature_foodies.data.repository.ProductsRepositoryImpl
import com.example.foodies.feature_foodies.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {


    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)


    @Provides
    @Singleton
    fun provideProductsRepository(
        api: ProductsApi
    ): ProductsRepository =
        ProductsRepositoryImpl(api)


}