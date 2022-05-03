package com.android.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.rickandmorty.data.network.CharactersPagingSource
import com.android.rickandmorty.data.network.RMService
import com.android.rickandmorty.data.network.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImp @Inject constructor(private val service: RMService) :
    CharacterRepository {

    override suspend fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                CharactersPagingSource(service)
            }
        ).flow
    }
}