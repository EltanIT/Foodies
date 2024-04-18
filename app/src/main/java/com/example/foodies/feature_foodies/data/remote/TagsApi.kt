package com.example.foodies.feature_foodies.data.remote

import com.example.foodies.feature_foodies.data.remote.dto.TagDto
import retrofit2.http.GET

interface TagsApi {

    @GET("Tags.json")
    suspend fun getTags(): List<TagDto>
}