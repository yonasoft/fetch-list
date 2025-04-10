package com.yonasoft.fetchlist.features.items.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yonasoft.fetchlist.features.items.domain.model.Item
import com.yonasoft.fetchlist.features.items.presentation.viewmodels.ItemsListingViewModel

@Composable
fun ItemsListing(viewModel: ItemsListingViewModel) {

    val itemsListingState = viewModel.itemsListingState.collectAsStateWithLifecycle()
    val sortedFilteredItems = itemsListingState.value.sortedFilteredItems
    val loading = itemsListingState.value.loading

    Scaffold {
        when (loading) {
            true -> Loading(it)
            false -> FilteredSortedItemsDisplayed(sortedFilteredItems, it)
        }
    }
}

@Composable
fun Loading(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.TopCenter,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilteredSortedItemsDisplayed(
    items: HashMap<Int, MutableList<Item>>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        if (items.size == 0) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Text(
                        "No items",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Blue,
                    )
                }
            }
        } else {
            for (listId in items.keys.sorted()) {
                stickyHeader {
                    Text(
                        "List Id: $listId (${items[listId]!!.size})",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Blue,
                    )
                    HorizontalDivider(color = Color.Blue)
                }
                items[listId]?.size?.let {
                    items(
                        it,
                        key = { i -> items[listId]!![i].id }
                    ) { i ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween
                        ) {
                            Text(text = "Item Id:" + items[listId]?.get(i)?.id.toString())
                            Text(text = "Item Name: " + items[listId]?.get(i)?.name.toString())
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
