package com.example.imagesearch.data.network.model


import com.google.gson.annotations.SerializedName

class UserProfileImageDto(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String,
)