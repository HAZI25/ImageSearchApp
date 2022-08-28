package com.example.imagesearch.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhotoUrls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String,
): Parcelable