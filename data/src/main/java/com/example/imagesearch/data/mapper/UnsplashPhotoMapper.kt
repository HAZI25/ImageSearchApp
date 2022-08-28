package com.example.imagesearch.data.mapper

import com.example.imagesearch.data.network.model.UnsplashPhotoDto
import com.example.imagesearch.data.network.model.UnsplashPhotoUrlsDto
import com.example.imagesearch.data.network.model.UserDto
import com.example.imagesearch.data.network.model.UserProfileImageDto
import com.example.imagesearch.domain.model.UnsplashPhoto
import com.example.imagesearch.domain.model.UnsplashPhotoUrls
import com.example.imagesearch.domain.model.User
import com.example.imagesearch.domain.model.UserProfileImage
import javax.inject.Inject

class UnsplashPhotoMapper @Inject constructor() {

    fun mapDtoToEntity(dto: UnsplashPhotoDto): UnsplashPhoto {
        return with(dto) {
            UnsplashPhoto(
                description = description,
                id = id,
                likes = likes,
                photoUrls = mapUrlsDtoToEntity(photoUrls),
                user = mapUserDtoToEntity(user)
            )
        }
    }

    private fun mapUrlsDtoToEntity(dto: UnsplashPhotoUrlsDto): UnsplashPhotoUrls {
        return with(dto) {
            UnsplashPhotoUrls(
                full, raw, regular, small, thumb
            )
        }
    }

    private fun mapUserDtoToEntity(dto: UserDto): User {
        return with(dto) {
            User(
                firstName, id, name, mapProfileImageDtoToEntity(profileImage), username
            )
        }
    }

    private fun mapProfileImageDtoToEntity(dto: UserProfileImageDto): UserProfileImage {
        return with(dto) {
            UserProfileImage(large, medium, small)
        }
    }
}