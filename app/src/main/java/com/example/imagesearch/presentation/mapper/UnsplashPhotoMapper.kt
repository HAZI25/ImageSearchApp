package com.example.imagesearch.presentation.mapper

import com.example.imagesearch.domain.model.UnsplashPhoto
import com.example.imagesearch.domain.model.UnsplashPhotoUrls
import com.example.imagesearch.domain.model.User
import com.example.imagesearch.domain.model.UserProfileImage
import javax.inject.Inject

class UnsplashPhotoMapper @Inject constructor() {
    fun mapDomainToApp(domain: UnsplashPhoto): com.example.imagesearch.presentation.model.UnsplashPhoto {
        return with(domain) {
            com.example.imagesearch.presentation.model.UnsplashPhoto(
                description = description,
                id = id,
                likes = likes,
                photoUrls = mapUrlsDomainToApp(photoUrls),
                user = mapUserDomainToApp(user)
            )
        }
    }

    private fun mapUrlsDomainToApp(domain: UnsplashPhotoUrls): com.example.imagesearch.presentation.model.UnsplashPhotoUrls {
        return with(domain) {
            com.example.imagesearch.presentation.model.UnsplashPhotoUrls(
                full, raw, regular, small, thumb
            )
        }
    }

    private fun mapUserDomainToApp(domain: User): com.example.imagesearch.presentation.model.User {
        return with(domain) {
            com.example.imagesearch.presentation.model.User(
                firstName, id, name, mapProfileImageDomainToApp(profileImage), username
            )
        }
    }

    private fun mapProfileImageDomainToApp(domain: UserProfileImage): com.example.imagesearch.presentation.model.UserProfileImage {
        return with(domain) {
            com.example.imagesearch.presentation.model.UserProfileImage(large, medium, small)
        }
    }
}