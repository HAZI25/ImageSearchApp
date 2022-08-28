package com.example.imagesearch.di

import com.example.imagesearch.common.Constants.BASE_URL
import com.example.imagesearch.data.network.service.UnsplashService
import com.example.imagesearch.data.repository.UnsplashRepositoryImpl
import com.example.imagesearch.domain.repository.UnsplashRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindsUnsplashRepository(impl: UnsplashRepositoryImpl): UnsplashRepository


    companion object {
        @Provides
        @ApplicationScope
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @ApplicationScope
        fun provideUnsplashApi(retrofit: Retrofit): UnsplashService {
            return retrofit.create(UnsplashService::class.java)
        }
    }
}