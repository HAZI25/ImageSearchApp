package com.example.imagesearch.data.network.model


import com.google.gson.annotations.SerializedName

class UnsplashPhotoDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("urls")
    val photoUrls: UnsplashPhotoUrlsDto,
    @SerializedName("user")
    val user: UserDto,
)