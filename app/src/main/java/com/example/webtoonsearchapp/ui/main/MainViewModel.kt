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
import kotlinx.coroutines.flow.combine
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
        .combine(state) { pagingData, uiState ->
            val bookMarkIds = uiState.bookMarkList.map { it.id }.toSet()
            pagingData.map { image ->
                image.copy(isBookMark = bookMarkIds.contains(image.id))
            }
        }

    init {
        getBookMarkList()
    }

    private fun getBookMarkList() {
        viewModelScope.launch {
            getBookMarkListUseCase().collect { result ->
                val updatedList = result.map { bookMark ->
                    imageUiMapper.mapToImageUiModel(bookMark)
                }
                setState {
                    copy(
                        bookMarkList = updatedList,
                        selectedList = updatedList.toSet()
                    )
                }
            }
        }
    }

    private fun clickBookMark(imageUiModel: ImageUiModel) {
        val isSelected = imageUiModel in currentState.selectedList
        val updatedSelectedList = if (isSelected) {
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
                bookMarkItems = currentState.selectedList.map { image -> imageUiMapper.mapToImageEntity(image) }
            )
            setState {
                copy(
                    isSelectionMode = false
                )
            }
        }
    }

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.LongClickList -> {
                setState {
                    copy(
                        isSelectionMode = true
                    )
                }
            }

            is MainUiEvent.ClickBookMark -> {
                clickBookMark(event.imageUiModel)
            }

            is MainUiEvent.SaveBookMark -> {
                saveBookMark()
            }

            is MainUiEvent.CancelBookMark -> {
                setState {
                    copy(
                        selectedList = emptySet(),
                        isSelectionMode = false,
                    )
                }
            }

            is MainUiEvent.ClickImageItem -> {
                setEffect {
                    MainUiEffect.NavigateToViewer(event.imageUiModel.id)
                }
            }

            is MainUiEvent.OnTabSelected -> {
//                setState {
//                    copy(
//                        isSelectionMode = false,
//                        selectedList = bookMarkList.toSet()
//                    )
//                }// TODO :!!!
            }
        }
    }
}