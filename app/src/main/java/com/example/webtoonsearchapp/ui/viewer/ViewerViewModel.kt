package com.example.webtoonsearchapp.ui.viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.usecase.GetImageUrlListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ViewerViewModel @Inject constructor(
    private val getImageUrlListUseCase: GetImageUrlListUseCase
): ViewModel() {
    val pagingFlow: Flow<PagingData<String>> = getImageUrlListUseCase()
        .cachedIn(viewModelScope)
}