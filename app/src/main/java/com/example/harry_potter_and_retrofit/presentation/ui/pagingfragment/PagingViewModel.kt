package com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class PagingViewModel @Inject constructor(
    val usecase: GetCharacterPagerUseCase,
) : ViewModel() {


    val items = usecase()
        .flow
        .cachedIn(viewModelScope)


}
