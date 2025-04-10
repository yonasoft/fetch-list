package com.yonasoft.fetchlist.features.items.data.remote.services

import android.util.Log
import com.yonasoft.fetchlist.features.items.domain.model.Item
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class ItemsService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getItems(): List<Item> {
        val response = client.get("https://fetch-hiring.s3.amazonaws.com/hiring.json")
        val body: List<Item>  = response.body()
        Log.i("items", body.toString())
        client.close()
        return body
    }
}
