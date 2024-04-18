package com.example.foodies.feature_foodies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Дата класс объекта корзины
@Entity
data class ProductInCart(
    @PrimaryKey(true)
    val primaryId: Int? = null,
    val id: Int? = null,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val price_current: Int = 0,
    val price_old: Int? = null,
    val count: Int? = 1,
)


//Функция для преобразования объект корзины товара в продукт
fun ProductInCart.toProductInCart(): Product{
    return Product(
        id = id,
        name = name,
        description = description,
        image = image,
        price_current = price_current,
        price_old = price_old,
    )
}