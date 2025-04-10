package com.yonasoft.fetchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yonasoft.fetchlist.features.items.presentation.screens.ItemsListing
import com.yonasoft.fetchlist.features.items.presentation.viewmodels.ItemsListingViewModel
import com.yonasoft.fetchlist.ui.theme.FetchListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchListTheme {
                ItemsListing(viewModel = ItemsListingViewModel())
            }
        }
    }
}

