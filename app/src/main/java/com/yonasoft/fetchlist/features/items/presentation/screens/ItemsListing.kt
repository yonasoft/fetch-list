package com.yonasoft.fetchlist.features.items.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yonasoft.fetchlist.features.items.presentation.viewmodels.ItemsListingViewModel

@Composable
fun ItemsListing(viewModel: ItemsListingViewModel) {

    val itemsListingState = viewModel.itemsListingState.collectAsStateWithLifecycle()
    val items = itemsListingState.value.initialItems

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            items(items.size,
                key = { i -> items[i].id }
            ) { i ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(text = "List ID: " + items[i].listId.toString())
                    Text(text = "Item Id:" + items[i].id.toString())
                    Text(text = "Item Name " + items[i].name.toString())
                }
                HorizontalDivider()
            }
        }
    }
}