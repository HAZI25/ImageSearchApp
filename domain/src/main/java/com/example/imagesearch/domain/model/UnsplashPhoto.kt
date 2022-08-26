package com.example.imagesearch.domain.model

data class UnsplashPhoto(
    val description: String,
    val id: String,
    val likes: Int,
    val photoUrls: UnsplashPhotoUrls,
    val user: User,
)