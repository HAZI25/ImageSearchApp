package com.example.imagesearch.domain.repository

import androidx.paging.PagingData
import com.example.imagesearch.domain.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {

    fun getSearchResult(query: String): Flow<PagingData<UnsplashPhoto>>
}