package com.example.imagesearch.domain.use_case

import androidx.paging.PagingData
import com.example.imagesearch.domain.model.UnsplashPhoto
import com.example.imagesearch.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<UnsplashPhoto>> {
        return unsplashRepository.getSearchResult(query)
    }
}