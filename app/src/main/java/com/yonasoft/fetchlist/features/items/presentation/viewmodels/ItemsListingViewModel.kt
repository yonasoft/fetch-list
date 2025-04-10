package com.yonasoft.fetchlist.features.items.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.fetchlist.features.items.data.repository.ItemsRepositoryImpl
import com.yonasoft.fetchlist.features.items.domain.repository.ItemsRepository
import com.yonasoft.fetchlist.features.items.presentation.state.ItemsListingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemsListingViewModel : ViewModel() {
    private val repository: ItemsRepository = ItemsRepositoryImpl()

    private val _itemsListingState = MutableStateFlow(ItemsListingState())
    val itemsListingState = _itemsListingState.asStateFlow()

    init {
        initializeItems()
    }

    private fun initializeItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _itemsListingState.update {
                it.copy(loading = true)
            }
            val items = repository.getItems()
            _itemsListingState.update {
                it.copy(loading = true, initialItems = items)
            }
        }
    }
}