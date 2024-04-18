package com.example.foodies.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.foodies.feature_foodies.data.data_sourse.ClientDataBase
import com.example.foodies.feature_foodies.data.repository.CartRepositoryImpl
import com.example.foodies.feature_foodies.domain.repository.CartRepository
import com.example.foodies.feature_foodies.domain.useCase.Card.AddItemInCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.CartUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.ClearCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.DeleteItemFromCardUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.GetAllCardItemsUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.GetItemFromCardByIdUseCase
import com.example.foodies.feature_foodies.domain.useCase.Card.UpdateItemInCardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CardModule {


    @Provides
    @Singleton
    fun provideCardDataStore(
        app: Application
    ): ClientDataBase {
        return Room.databaseBuilder(
            app,
            ClientDataBase::class.java,
            ClientDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideCardRepository(
        dataBase: ClientDataBase
    ): CartRepository =
        CartRepositoryImpl(dataBase.cartDao)


    @Provides
    @Singleton
    fun provideCartUseCase(
        repository: CartRepository
    ): CartUseCase =
        CartUseCase(
            GetAllCardItemsUseCase(repository),
            AddItemInCardUseCase(repository),
            ClearCardUseCase(repository),
            DeleteItemFromCardUseCase(repository),
            GetItemFromCardByIdUseCase(repository),
            UpdateItemInCardUseCase(repository)
        )


}