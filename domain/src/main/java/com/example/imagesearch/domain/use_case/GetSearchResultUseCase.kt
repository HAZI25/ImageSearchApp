package com.example.imagesearch.domain.use_case

import com.example.imagesearch.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository,
) {
    operator fun invoke(query: String) {
        unsplashRepository.getSearchResult(query)
    }
}