package com.example.foodies.feature_foodies.data.data_sourse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import kotlinx.coroutines.flow.Flow


// Интерфейс для взаимодействия с таблицей бд - корзиной
@Dao
interface CartDao {

    //Получить все
    @Query("SELECT * FROM ProductInCart")
    fun getAll(): Flow<List<ProductInCart>>

    //Получить продукт по id
    @Query("SELECT * FROM ProductInCart WHERE id = :id")
    fun getItemById(id: Int): Flow<ProductInCart?>

    //Добавить продукт в корзину
    @Insert
    fun addProduct(product: ProductInCart)

    //Удалить продукт из корзины
    @Query("DELETE FROM ProductInCart WHERE id = :id")
    fun deleteProduct(id: Int)

    //Обновить колличество продукта в корзине
    @Query("UPDATE ProductInCart SET count = :count WHERE id = :id")
    fun updateCount(id: Int, count: Int)

    //Очистить корзину
    @Query("DELETE FROM ProductInCart")
    suspend fun clearCard()
}