package com.yonasoft.fetchlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import com.yonasoft.fetchlist.features.items.data.services.ItemsService
import com.yonasoft.fetchlist.ui.theme.FetchListTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchListTheme {
                LaunchedEffect(Unit) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        try {
                            val service = ItemsService()
                            val response = service.getItems()
                            Log.d("TEST_FUNCTION", "Response: $response")
                        } catch (e: Exception) {
                            Log.e("TEST_FUNCTION", "Error testing function", e)

                        }
                    }
                }
            }
        }
    }
}

