package com.example.foodies.feature_foodies.domain.useCase.Tags

import com.example.foodies.feature_foodies.data.remote.dto.toTag
import com.example.foodies.feature_foodies.domain.model.Tag
import com.example.foodies.feature_foodies.domain.repository.TagsRepository
import javax.inject.Inject

class GetTagById @Inject constructor(
    private val tagsRepository: TagsRepository
) {

    suspend operator fun invoke(id: Int): Tag? {
        return tagsRepository.getTagsById(id)?.toTag()
    }
}