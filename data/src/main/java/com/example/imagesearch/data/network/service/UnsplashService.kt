package com.example.imagesearch.data.network.service

import com.example.imagesearch.data.BuildConfig
import com.example.imagesearch.data.network.model.UnsplashPhotoResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashService {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query(QUERY_PARAM_QUERY) query: String,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_PER_PAGE) perPage: Int,
    ): UnsplashPhotoResponseDto

    companion object {
        private const val QUERY_PARAM_QUERY = "query"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PER_PAGE = "per_page"

        const val CLIENT_ID = BuildConfig.API_KEY
    }
}