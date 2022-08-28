package com.example.imagesearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.imagesearch.data.mapper.UnsplashPhotoMapper
import com.example.imagesearch.data.network.paging.UnsplashPagingSource
import com.example.imagesearch.data.network.service.UnsplashService
import com.example.imagesearch.domain.model.UnsplashPhoto
import com.example.imagesearch.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashService,
    private val mapper: UnsplashPhotoMapper,
) : UnsplashRepository {

    override fun getSearchResult(query: String): Flow<PagingData<UnsplashPhoto>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 50,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            UnsplashPagingSource(unsplashApi, query)
        }
    ).flow.map { pagingData ->
        pagingData.map { dto ->
            mapper.mapDtoToEntity(dto)
        }
    }
}