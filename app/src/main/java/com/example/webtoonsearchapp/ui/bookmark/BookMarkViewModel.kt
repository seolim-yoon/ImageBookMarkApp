package com.example.webtoonsearchapp.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ClickBookMarkUseCase
import com.example.domain.usecase.GetBookMarkListUseCase
import com.example.webtoonsearchapp.base.BaseViewModel
import com.example.webtoonsearchapp.mapper.ImageUiMapper
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.bookmark.contract.BookMarkUiEffect
import com.example.webtoonsearchapp.ui.bookmark.contract.BookMarkUiEvent
import com.example.webtoonsearchapp.ui.bookmark.contract.BookMarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val getBookMarkListUseCase: GetBookMarkListUseCase,
    private val clickBookMarkUseCase: ClickBookMarkUseCase,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<BookMarkUiState, BookMarkUiEvent, BookMarkUiEffect>() {
    override fun createInitialState(): BookMarkUiState = BookMarkUiState()

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
                        bookMarkList = updatedList
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
                isAdd = false,
                bookMarkItems = currentState.selectedList.map { image -> imageUiMapper.mapToImageEntity(image) }
            )
            setState {
                copy(
                    isSelectionMode = false
                )
            }
        }
    }

    override fun onEvent(event: BookMarkUiEvent) {
        when (event) {
            is BookMarkUiEvent.LongClickList -> {
                setState {
                    copy(
                        isSelectionMode = true
                    )
                }
            }

            is BookMarkUiEvent.ClickBookMark -> {
                clickBookMark(event.imageUiModel)
            }

            is BookMarkUiEvent.SaveBookMark -> {
                saveBookMark()
            }

            is BookMarkUiEvent.CancelBookMark -> {
                setState {
                    copy(
                        selectedList = emptySet(),
                        isSelectionMode = false,
                    )
                }
            }

            is BookMarkUiEvent.ClickImageItem -> {
                setEffect {
                    BookMarkUiEffect.NavigateToViewer(event.imageUiModel.id)
                }
            }
        }
    }
}