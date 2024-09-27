package com.example.harry_potter_and_retrofit.data.paging.repoimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.harry_potter_and_retrofit.data.paging.pagingsource.CharacterPagingSource
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterPagingRepository



class CharacterPagingRepositoryImpl : CharacterPagingRepository {


    override fun getPager()= Pager(
            config = PagingConfig(ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource() }
        )

    companion object {
        private const val ITEMS_PER_PAGE = 100
    }
}

