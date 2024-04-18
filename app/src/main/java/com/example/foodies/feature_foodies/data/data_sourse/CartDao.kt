package com.example.foodies.feature_foodies.data.data_sourse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodies.feature_foodies.domain.model.ProductInCart
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Query("SELECT * FROM ProductInCart")
    fun getAll(): Flow<List<ProductInCart>>

    @Query("SELECT * FROM ProductInCart WHERE id = :id")
    fun getItemById(id: Int): Flow<ProductInCart?>

    @Insert
    fun addProduct(product: ProductInCart)

    @Query("DELETE FROM ProductInCart WHERE id = :id")
    fun deleteProduct(id: Int)

    @Query("UPDATE ProductInCart SET count = :count WHERE id = :id")
    fun updateCount(id: Int, count: Int)


    @Query("DELETE FROM ProductInCart")
    suspend fun clearCard()
}