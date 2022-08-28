package com.example.imagesearch.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imagesearch.common.likeStateFlow
import com.example.imagesearch.domain.model.UnsplashPhoto
import com.example.imagesearch.domain.use_case.GetSearchResultUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GalleryViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
) : ViewModel() {

    private val currentQuery = MutableStateFlow(DEFAULT_QUERY)

    private val photosResult = currentQuery.flatMapLatest {
        getSearchResultUseCase(it).cachedIn(viewModelScope)
    }

    val photos: StateFlow<PagingData<UnsplashPhoto>> =
        photosResult.likeStateFlow(viewModelScope, PagingData.empty())

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "cats"
    }
}