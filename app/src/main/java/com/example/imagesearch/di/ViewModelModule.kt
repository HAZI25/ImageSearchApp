package com.example.imagesearch.di

import androidx.lifecycle.ViewModel
import com.example.imagesearch.presentation.gallery.GalleryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @ViewModelKey(GalleryViewModel::class)
    @IntoMap
    fun bindCoinViewModel(viewModel: GalleryViewModel): ViewModel
}