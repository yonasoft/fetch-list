package com.yonasoft.fetchlist.features.items.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val listId: Int,
    val name: String?
)