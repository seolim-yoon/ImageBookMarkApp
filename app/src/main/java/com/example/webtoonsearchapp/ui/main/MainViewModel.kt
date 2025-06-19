package com.example.webtoonsearchapp.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.ClickBookMarkUseCase
import com.example.domain.usecase.GetBookMarkListUseCase
import com.example.domain.usecase.GetMainImageListUseCase
import com.example.webtoonsearchapp.base.BaseViewModel
import com.example.webtoonsearchapp.mapper.ImageUiMapper
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.contract.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    getMainImageListUseCase: GetMainImageListUseCase,
    private val getBookMarkListUseCase: GetBookMarkListUseCase,
    private val clickBookMarkUseCase: ClickBookMarkUseCase,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<MainUiState, MainUiEvent, MainUiEffect>() {
    override fun createInitialState(): MainUiState = MainUiState()

    val pagingFlow: Flow<PagingData<ImageUiModel>> = getMainImageListUseCase()
        .map { pagingData ->
            pagingData.map { entity -> imageUiMapper.mapToImageUiModel(entity) }
        }
        .cachedIn(viewModelScope)

    private fun longClickList() {
        setState {
            copy(
                isSelectionMode = true
            )
        }
    }

    private fun clickBookMark(imageUiModel: ImageUiModel) {
        val isAlreadySelected = imageUiModel in currentState.selectedList
        val updatedSelectedList = if (isAlreadySelected) {
            currentState.selectedList - imageUiModel
        } else {
            currentState.selectedList + imageUiModel
        }

        setState {
            copy(selectedList = updatedSelectedList)
        }
    }

    private fun saveBookMark() {
        viewModelScope.launch(Dispatchers.IO) {
            clickBookMarkUseCase(
                isAdd = true,
                bookMarkItems = currentState.selectedList.map { image -> imageUiMapper.mapToImageEntity(image) }
            )
            setState {
                copy(
                    isSelectionMode = false
                )
            }
        }
    }

    private fun cancelBookMark() {
        setState {
            copy(
                isSelectionMode = false,
                selectedList = emptySet()
            )
        }
    }

    private fun clickImageItem(url: String) {
        setEffect {
            MainUiEffect.NavigateToViewer(url)
        }
    }

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.LongClickList -> {
                longClickList()
            }

            is MainUiEvent.ClickBookMark -> {
                clickBookMark(event.imageUiModel)
            }

            is MainUiEvent.SaveBookMark -> {
                saveBookMark()
            }

            is MainUiEvent.CancelBookMark -> {
                cancelBookMark()
            }

            is MainUiEvent.ClickImageItem -> {
                clickImageItem(event.imageUiModel.link)
            }
        }
    }
}