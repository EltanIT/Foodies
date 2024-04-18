package com.example.foodies.feature_foodies.domain.repository

import com.example.foodies.feature_foodies.data.remote.dto.TagDto

interface TagsRepository {

    suspend fun getTagsById(id: Int): TagDto?

    suspend fun getTags(): List<TagDto>
}