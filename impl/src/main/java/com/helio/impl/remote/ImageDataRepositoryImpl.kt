package com.helio.impl.remote

import com.helio.api.model.ImageModel
import com.helio.api.repository.ImageDataRepository
import com.helio.impl.remote.model.toImageModels
import javax.inject.Inject

class ImageDataRepositoryImpl @Inject constructor(
    private val api: GiphyApi
): ImageDataRepository {
    override suspend fun loadImages(query: String, offset: Int, limit: Int): List<ImageModel> {
        return api.loadByQuery(query, offset, limit).toImageModels()
    }
}