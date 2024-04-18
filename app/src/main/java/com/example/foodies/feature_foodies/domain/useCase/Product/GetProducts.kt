package com.example.foodies.feature_foodies.domain.useCase.Product

import com.example.foodies.common.Constants
import com.example.foodies.common.Resource
import com.example.foodies.feature_foodies.data.remote.dto.toProduct
import com.example.foodies.feature_foodies.domain.model.Product
import com.example.foodies.feature_foodies.domain.repository.ProductsRepository
import com.example.foodies.feature_foodies.domain.util.ProductOrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val repository: ProductsRepository
) {
    
    suspend operator fun invoke(order: Array<out ProductOrderType>): Flow<Resource<List<Product>>>{
        return flow {
            try {
                emit(Resource.Loading<List<Product>>())

                var products = emptyList<Product>()
                products = repository.getProducts().map { it.toProduct().copy(
                    image = "https://s3-alpha-sig.figma.com/img/6861/f5d0/b3bbfc363f115fdeba2e726f1f1a6c29?Expires=1714348800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ctXkpwORAen4u8DsjjIoHxAeG44LCLHyzGcQxSIN2kkByIea~DWxe8OqtNa8W1X2EJWt3C4teCXK~AzVXboaM6WGRxhpwrxCZKW9kJOTw6AFppfbRkIGuVDhFHPYI7prfnYP7DSdLizZdrrpBwg258p7Pm~z5~TtbZjGPiPVjuKcZjHJrZAMjj0yLQv8kK-Mwhmd6ax4R0uYRc55S3xnqjtCNs9Mh3O6ocwxPnV0eW8HPa5Zwn296sS7Sxr4PMIf4ZuceDsnyzFEx5pCygVyHci~jbmKmu~NtPQQkHGB3hkrbDg2LpGOiR2-MIdeAKzolvEqccXcVrtnZ2KvvgiL-A__"
                )}

                for (type in order){
                    when(type){
                        is ProductOrderType.Category -> {
                            products = products.filter {
                                it.category_id == type.id
                            }
                        }
                        is ProductOrderType.Search -> {
                            products = products.filter {
                                it.name.lowercase().contains(type.value.lowercase())
                            }
                        }
                        is ProductOrderType.Tag -> {
                            products = products.filter {
                                if (it.tag_ids.size==type.ids.size){
                                    it.tag_ids.sorted() == type.ids.sorted()
                                }else if(it.tag_ids.size > type.ids.size){
                                    type.ids.any(it.tag_ids::contains)
                                }else{
                                    false
                                }
                            }
                        }
                    }
                }

                emit(Resource.Success<List<Product>>(products))

            }catch (e: HttpException){
                emit(Resource.Error<List<Product>>(Constants.ExceptionMessages.HttpExceptionMessage))
            }catch (e: IOException){
                emit(Resource.Error<List<Product>>(Constants.ExceptionMessages.IOExceptionMessage))
            }

        }

    }
}