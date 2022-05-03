package com.android.rickandmorty.data.network

import com.android.rickandmorty.data.network.model.Response

interface RMService {
    suspend fun getCharacters(): Response
}