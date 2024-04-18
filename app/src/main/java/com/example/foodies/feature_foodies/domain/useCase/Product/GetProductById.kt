package com.example.foodies.feature_foodies.domain.useCase.Product

import com.example.foodies.common.Constants
import com.example.foodies.common.Resource
import com.example.foodies.feature_foodies.data.remote.dto.toProduct
import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductById @Inject constructor(
    private val repository: ProductsRepository
) {
    
    suspend operator fun invoke(
        id: Int
    ): Flow<Resource<Product>>{

        return flow {
            try {
                emit(Resource.Loading<Product>())

                val product = repository.getProductById(id).toProduct().copy(
                    image = "https://s3-alpha-sig.figma.com/img/6861/f5d0/b3bbfc363f115fdeba2e726f1f1a6c29?Expires=1714348800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ctXkpwORAen4u8DsjjIoHxAeG44LCLHyzGcQxSIN2kkByIea~DWxe8OqtNa8W1X2EJWt3C4teCXK~AzVXboaM6WGRxhpwrxCZKW9kJOTw6AFppfbRkIGuVDhFHPYI7prfnYP7DSdLizZdrrpBwg258p7Pm~z5~TtbZjGPiPVjuKcZjHJrZAMjj0yLQv8kK-Mwhmd6ax4R0uYRc55S3xnqjtCNs9Mh3O6ocwxPnV0eW8HPa5Zwn296sS7Sxr4PMIf4ZuceDsnyzFEx5pCygVyHci~jbmKmu~NtPQQkHGB3hkrbDg2LpGOiR2-MIdeAKzolvEqccXcVrtnZ2KvvgiL-A__"
                )

                if (product == null){
                    throw IOException()
                }else{
                    emit(Resource.Success<Product>(product))
                }
            }catch (e: HttpException){
                emit(Resource.Error<Product>(Constants.ExceptionMessages.HttpExceptionMessage))
            }catch (e: IOException){
                emit(Resource.Error<Product>(Constants.ExceptionMessages.IOExceptionMessage))
            }

        }

    }
}