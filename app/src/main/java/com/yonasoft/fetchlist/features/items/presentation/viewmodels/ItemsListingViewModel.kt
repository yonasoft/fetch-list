package com.yonasoft.fetchlist.features.items.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.fetchlist.features.items.data.remote.repository.ItemsRepositoryImpl
import com.yonasoft.fetchlist.features.items.domain.model.Item
import com.yonasoft.fetchlist.features.items.domain.repository.ItemsRepository
import com.yonasoft.fetchlist.features.items.presentation.state.ItemsListingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsListingViewModel : ViewModel() {
    private val repository: ItemsRepository = ItemsRepositoryImpl()

    private val _itemsListingState = MutableStateFlow(ItemsListingState())
    val itemsListingState = _itemsListingState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _itemsListingState.update {
                    it.copy(loading = true)
                }
            }

            initializeItems()
            sortAndFilterItems()

            withContext(Dispatchers.Main) {
                _itemsListingState.update {
                    it.copy(loading = false)
                }
            }
        }
    }

    private suspend fun initializeItems() {
        val items = repository.getItems()

        withContext(Dispatchers.Main) {
            _itemsListingState.update {
                it.copy(initialItems = items)
            }
        }
        Log.i("items", "initial items: ${itemsListingState.value.initialItems}")
    }

    private suspend fun sortAndFilterItems() {

        val sortedFilteredItems = HashMap<Int, MutableList<Item>>()
        for (item in itemsListingState.value.initialItems) {
            if (item.listId !in sortedFilteredItems) sortedFilteredItems[item.listId] =
                mutableListOf()
            if (!item.name.isNullOrEmpty()) sortedFilteredItems[item.listId]?.add(item)
        }

        withContext(Dispatchers.Main) {
            _itemsListingState.update {
                it.copy(sortedFilteredItems = sortedFilteredItems)
            }
        }
    }
}