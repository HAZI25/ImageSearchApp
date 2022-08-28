package com.example.imagesearch.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.imagesearch.common.likeStateFlow
import com.example.imagesearch.domain.use_case.GetSearchResultUseCase
import com.example.imagesearch.presentation.mapper.UnsplashPhotoMapper
import com.example.imagesearch.presentation.model.UnsplashPhoto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GalleryViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val mapper: UnsplashPhotoMapper,
) : ViewModel() {

    private val currentQuery = MutableStateFlow(DEFAULT_QUERY)

    private val photosResult = currentQuery.flatMapLatest {
        getSearchResultUseCase(it).cachedIn(viewModelScope)
    }.map {
        it.map {
            mapper.mapDomainToApp(it)
        }
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