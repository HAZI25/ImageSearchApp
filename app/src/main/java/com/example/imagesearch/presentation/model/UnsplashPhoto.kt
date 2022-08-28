package com.example.imagesearch.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val description: String?,
    val id: String,
    val likes: Int,
    val photoUrls: UnsplashPhotoUrls,
    val user: User,
) : Parcelable