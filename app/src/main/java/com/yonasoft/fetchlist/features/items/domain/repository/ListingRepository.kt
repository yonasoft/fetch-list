package com.yonasoft.fetchlist.features.items.domain.repository

import com.yonasoft.fetchlist.features.items.domain.model.Item

interface ItemsRepository {
    suspend fun getItems():List<Item>
}