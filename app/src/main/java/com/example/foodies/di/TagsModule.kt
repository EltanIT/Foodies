package com.example.foodies.di

import com.example.foodies.common.Constants
import com.example.foodies.feature_foodies.data.remote.TagsApi
import com.example.foodies.feature_foodies.data.repository.TagsRepositoryImpl
import com.example.foodies.feature_foodies.domain.repository.TagsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TagsModule {


    @Provides
    @Singleton
    fun provideTagsApi(): TagsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TagsApi::class.java)

    @Provides
    @Singleton
    fun provideTagsRepository(
        api: TagsApi
    ): TagsRepository =
        TagsRepositoryImpl(api)



}