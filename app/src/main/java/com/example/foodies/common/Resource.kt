package com.example.foodies.common


//Класс для обработки информции с api из useCase и дальнейшей передачи в модель представления
sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}
