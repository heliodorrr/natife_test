package com.helio.api

import com.helio.api.model.ImageModel

interface LoadImageListUseCase {
    suspend operator fun invoke(query: String, offset: Int, limit: Int): List<ImageModel>
}