package com.example.webtoonsearchapp.ui.main

import android.util.Log
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
    private val getMainImageListUseCase: GetMainImageListUseCase,
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
                Log.v("seolim", "result : " + result.size)
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
        val updatedBookMark = !imageUiModel.isBookMark

       viewModelScope.launch(Dispatchers.IO) {
           val isSuccess = clickBookMarkUseCase(
                isAdd = updatedBookMark,
                bookMarkItem = imageUiMapper.mapToImageEntity(imageUiModel)
            )
           if (isSuccess) {
            // 성공했을 때만 pagingFlow 변경되도록
           }
        }
    }

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.LoadMore -> {

            }

            is MainUiEvent.Refresh -> {

            }

            is MainUiEvent.InputKeyword -> {

            }

            is MainUiEvent.ClickBookMark -> {
                clickBookMark(event.imageUiModel)
            }

            is MainUiEvent.ClickImageItem -> {

            }
        }
    }
}