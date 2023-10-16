package com.helio.api.repository

import com.helio.api.model.ImageModel

interface ImageDataRepository {
    suspend fun loadImages(query: String, offset: Int, limit: Int): List<ImageModel>
}