package com.yonasoft.fetchlist.features.items.presentation.state

import com.yonasoft.fetchlist.features.items.domain.model.Item

data class ItemsListingState(
    val initialItems: List<Item> = emptyList(),
    val loading:Boolean =  false
)