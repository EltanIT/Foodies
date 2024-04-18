package com.example.foodies.feature_foodies.data.repository

import com.example.foodies.feature_foodies.data.remote.TagsApi
import com.example.foodies.feature_foodies.data.remote.dto.TagDto
import com.example.foodies.feature_foodies.domain.repository.TagsRepository

class TagsRepositoryImpl(
    private val tagsApi: TagsApi
): TagsRepository {

    override suspend fun getTagsById(id: Int): TagDto? {
        return tagsApi.getTags().find { it.id == id }
    }

    override suspend fun getTags(): List<TagDto> {
        return tagsApi.getTags()
    }
}