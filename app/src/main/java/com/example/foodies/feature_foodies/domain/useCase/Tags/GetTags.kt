package com.example.foodies.feature_foodies.domain.useCase.Tags

import com.example.foodies.common.Resource
import com.example.foodies.feature_foodies.data.remote.dto.toTag
import com.example.foodies.feature_foodies.domain.model.Tag
import com.example.foodies.feature_foodies.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTags @Inject constructor(
    private val repository: TagsRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Tag>>> {
        return flow {
            try {
                emit(Resource.Loading<List<Tag>>())
                val tags = repository.getTags().map { it.toTag() }
                emit(Resource.Success<List<Tag>>(tags))
            }catch (e: HttpException){
                emit(Resource.Error<List<Tag>>(e.message.toString()))
            }catch (e: IOException){
                emit(Resource.Error<List<Tag>>(e.message.toString()))
            }

        }
    }
}