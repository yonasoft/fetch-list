package com.yonasoft.fetchlist.features.items.data.repository

import com.yonasoft.fetchlist.features.items.data.services.ItemsService
import com.yonasoft.fetchlist.features.items.domain.model.Item
import com.yonasoft.fetchlist.features.items.domain.repository.ItemsRepository

class ItemsRepositoryImpl():ItemsRepository {
    private val itemsService = ItemsService()

    override suspend fun getItems():List<Item> {
        return itemsService.getItems()
    }
}