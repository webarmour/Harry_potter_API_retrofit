package com.example.harry_potter_and_retrofit.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem
import kotlinx.coroutines.delay
import kotlin.math.max

class CharacterPagingSource : PagingSource<Int, CharacterPagingItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterPagingItem> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize) //ITEMS_PER_PAGE/


       if (start != STARTING_KEY) delay(2000)
        return LoadResult.Page(
            data = range.map { number ->
                CharacterPagingItem(
                    id = number.toString(),
                    name = "Name of character $number"
                )
            },
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }


    override fun getRefreshKey(state: PagingState<Int, CharacterPagingItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val character = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(character.id.toInt() - state.config.pageSize/2)
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    companion object {
        private const val STARTING_KEY = 1
    }


}