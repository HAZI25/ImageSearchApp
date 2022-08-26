package com.example.imagesearch.data.network.model


import com.google.gson.annotations.SerializedName

class UnsplashPhotoResponseDto(
    @SerializedName("results")
    val unsplashPhotoList: List<UnsplashPhotoDto>,
)