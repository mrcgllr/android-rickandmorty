package com.android.rickandmorty.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.rickandmorty.data.network.model.Character

class CharactersPagingSource(
    private val service: RMService
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.getCharacters()
            LoadResult.Page(
                data = response.characters,
                prevKey = response.info.prev.toInt(), // Only paging forward.
                nextKey = response.info.next.toInt()
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}