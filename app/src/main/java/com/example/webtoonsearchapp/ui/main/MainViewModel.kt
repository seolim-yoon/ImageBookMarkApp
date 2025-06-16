package com.example.webtoonsearchapp.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.GetMainImageListUseCase
import com.example.webtoonsearchapp.base.BaseViewModel
import com.example.webtoonsearchapp.mapper.ImageUiMapper
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.contract.MainUiState
import com.example.webtoonsearchapp.util.MAIN_KEYWORD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMainImageListUseCase: GetMainImageListUseCase,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<MainUiState, MainUiEvent, MainUiEffect>() {
    override fun createInitialState(): MainUiState = MainUiState()

    val pagingFlow: Flow<PagingData<ImageUiModel>> = getMainImageListUseCase(MAIN_KEYWORD)
        .map { pagingData -> pagingData.map { image -> imageUiMapper.mapToImageUiModel(image) } }
        .cachedIn(viewModelScope)

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.LoadMore -> {

            }

            is MainUiEvent.Refresh -> {

            }

            is MainUiEvent.InputKeyword -> {

            }

            is MainUiEvent.ClickBookMark -> {

            }

            is MainUiEvent.ClickImageItem -> {

            }
        }
    }
}