package com.example.webtoonsearchapp.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.GetSearchImageListUseCase
import com.example.webtoonsearchapp.base.BaseViewModel
import com.example.webtoonsearchapp.mapper.ImageUiMapper
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.search.contract.SearchUiEffect
import com.example.webtoonsearchapp.ui.search.contract.SearchUiEvent
import com.example.webtoonsearchapp.ui.search.contract.SearchUiState
import com.example.webtoonsearchapp.util.SEARCH_TIME_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchImageListUseCase: GetSearchImageListUseCase,
    private val imageUiMapper: ImageUiMapper
) : BaseViewModel<SearchUiState, SearchUiEvent, SearchUiEffect>() {
    override fun createInitialState(): SearchUiState = SearchUiState()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagingFlow: Flow<PagingData<ImageUiModel>> = state
        .map { it.inputKeyword }
        .distinctUntilChanged()
        .debounce(SEARCH_TIME_DELAY)
        .filter { it.isNotBlank() }
        .flatMapLatest { keyword ->
            getSearchImageListUseCase(keyword).map { pagingData ->
                pagingData.map { entity -> imageUiMapper.mapToImageUiModel(entity) }
            }
        }.cachedIn(viewModelScope)

    private fun inputSearchKeyword(keyword: String) {
        setState {
            copy(
                inputKeyword = keyword
            )
        }
        setEffect {
            SearchUiEffect.ScrollToTop
        }
    }

    override fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.InputKeyword -> {
                inputSearchKeyword(event.keyword)
            }

            is SearchUiEvent.ClickImageItem -> {
                setEffect {
                    SearchUiEffect.NavigateToViewer(event.imageUiModel.link)
                }
            }
        }
    }
}