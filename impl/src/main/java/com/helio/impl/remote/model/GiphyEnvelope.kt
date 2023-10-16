package com.helio.impl.remote.model

import com.helio.api.model.ImageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class GiphyEnvelope(
    @SerialName("data")
    val data: List<GiphyPost>,
)

fun GiphyEnvelope.toImageModels(): List<ImageModel> {
    return data.map(GiphyPost::toImageModel)
}

