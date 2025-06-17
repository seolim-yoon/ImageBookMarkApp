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
import com.example.webtoonsearchapp.util.MAIN_KEYWORD
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

    val pagingFlow: Flow<PagingData<ImageUiModel>> = getMainImageListUseCase(MAIN_KEYWORD)
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
                setState {
                    copy(
                        bookMarkList = result.map { bookMark ->
                            imageUiMapper.mapToImageUiModel(bookMark)
                        }
                    )
                }
            }
        }
    }

    private fun clickBookMark(imageUiModel: ImageUiModel) {
       viewModelScope.launch(Dispatchers.IO) {
           clickBookMarkUseCase(
                isAdd = !imageUiModel.isBookMark,
                bookMarkItem = imageUiMapper.mapToImageEntity(imageUiModel)
            )
        }
    }

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.Refresh -> {

            }

            is MainUiEvent.ClickBookMark -> {
                clickBookMark(event.imageUiModel)
            }

            is MainUiEvent.ClickImageItem -> {

            }
        }
    }
}