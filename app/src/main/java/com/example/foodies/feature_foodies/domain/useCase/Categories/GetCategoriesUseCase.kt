package com.example.foodies.feature_foodies.domain.useCase.Categories

import com.example.foodies.common.Resource
import com.example.foodies.feature_foodies.data.remote.dto.toCategory
import com.example.foodies.feature_foodies.domain.model.Category
import com.example.foodies.feature_foodies.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {
    
    suspend operator fun invoke(): Flow<Resource<List<Category>>>{
        return flow {
            try {
                emit(Resource.Loading<List<Category>>())
                val products = repository.getCategories().map { it.toCategory() }
                emit(Resource.Success<List<Category>>(products))
            }catch (e: HttpException){
                emit(Resource.Error<List<Category>>(e.message.toString()))
            }catch (e: IOException){
                emit(Resource.Error<List<Category>>(e.message.toString()))
            }

        }
    }
}