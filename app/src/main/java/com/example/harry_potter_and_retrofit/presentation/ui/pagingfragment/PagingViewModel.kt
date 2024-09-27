package com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.harry_potter_and_retrofit.data.paging.repoimpl.CharacterPagingRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterPagerUseCase

class PagingViewModel() : ViewModel() {


    val usecase = GetCharacterPagerUseCase(CharacterPagingRepositoryImpl())

    val items = usecase()
        .flow
        .cachedIn(viewModelScope)

    fun getCharacterPage() {
        usecase()
    }

    companion object {
        private const val ITEMS_PER_PAGE = 100
    }
}
