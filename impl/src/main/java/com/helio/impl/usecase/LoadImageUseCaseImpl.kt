package com.helio.impl.usecase

import com.helio.api.LoadImageListUseCase
import com.helio.api.model.ImageModel
import com.helio.api.repository.ImageDataRepository
import javax.inject.Inject


class LoadImageUseCaseImpl @Inject constructor(
    private val repository: ImageDataRepository
): LoadImageListUseCase {
    override suspend fun invoke(query: String, offset: Int, limit: Int): List<ImageModel> {
        return repository.loadImages(query, offset, limit)
    }
}