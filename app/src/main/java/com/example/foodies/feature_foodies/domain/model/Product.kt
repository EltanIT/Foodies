package com.example.foodies.feature_foodies.domain.model

//Дата класс продуктов
data class Product(
    val id: Int? = null,
    val category_id: Int? = null,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val price_current: Int = 0,
    val price_old: Int? = null,
    val measure: Int = 0,
    val measure_unit: String = "",
    val energy_per_100_grams: Float = 0f,
    val proteins_per_100_grams: Float = 0f,
    val fats_per_100_grams: Float = 0f,
    val carbohydrates_per_100_grams: Float = 0f,
    val tag_ids: List<Int> = emptyList(),
)


//Функция для преобразования товара в объект корзины
fun Product.toProductInCart(): ProductInCart{
    return ProductInCart(
        id = id,
        name = name,
        description = description,
        image = image,
        price_current = price_current,
        price_old = price_old,
    )
}
