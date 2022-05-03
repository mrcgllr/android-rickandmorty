package com.android.rickandmorty.data.repository

import androidx.paging.PagingData
import com.android.rickandmorty.data.network.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters() : Flow<PagingData<Character>>
}