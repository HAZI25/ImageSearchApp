package com.example.imagesearch.domain.model

data class User(
    val firstName: String,
    val id: String,
    val name: String,
    val profileImage: UserProfileImage,
    val username: String,
)