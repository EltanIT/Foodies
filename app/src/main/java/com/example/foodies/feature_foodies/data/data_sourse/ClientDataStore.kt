package com.example.foodies.feature_foodies.data.data_sourse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodies.feature_foodies.domain.model.ProductInCart


//Класс для инициализации бд
@Database(
    entities = [ProductInCart::class],
    version = 4
)
abstract class ClientDataBase: RoomDatabase() {


    abstract val cartDao: CartDao

    companion object{
        const val DATABASE_NAME = "clientDB"
    }

}