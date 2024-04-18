package com.example.foodies.feature_foodies.data.remote.dto

import com.example.foodies.feature_foodies.domain.model.Tag


data class TagDto(
    val id: Int? = null,
    val name: String = "",
)

fun TagDto.toTag(): Tag {
    return Tag(
        id,
        name
    )
}
