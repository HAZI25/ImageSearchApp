package com.example.imagesearch.data.network.model


import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_image")
    val profileImage: UserProfileImageDto,
    @SerializedName("username")
    val username: String,
)