package com.example.imagesearch.di

import com.example.imagesearch.presentation.gallery.GalleryFragment
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: GalleryFragment)
}