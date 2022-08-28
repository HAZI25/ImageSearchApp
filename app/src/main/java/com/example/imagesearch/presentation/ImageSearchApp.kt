package com.example.imagesearch.presentation

import android.app.Application
import com.example.imagesearch.di.ApplicationComponent
import com.example.imagesearch.di.DaggerApplicationComponent

class ImageSearchApp : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}