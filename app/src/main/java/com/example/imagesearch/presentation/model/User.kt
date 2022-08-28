package com.example.imagesearch.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val id: String,
    val name: String,
    val profileImage: UserProfileImage,
    val username: String,
): Parcelable