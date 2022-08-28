package com.example.imagesearch.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearch.data.network.model.UnsplashPhotoDto
import com.example.imagesearch.data.network.service.UnsplashService
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val unsplashApi: UnsplashService,
    private val query: String,
) : PagingSource<Int, UnsplashPhotoDto>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoDto>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhotoDto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.unsplashPhotoList

            val prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1
            val nextKey = if (photos.isEmpty()) null else position + 1

            LoadResult.Page(photos, prevKey, nextKey)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}