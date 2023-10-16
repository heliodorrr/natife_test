package com.helio.impl.remote.model


import com.helio.api.model.ImageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GiphyPost(
    @SerialName("images")
    val images: GiphyImages
)

fun GiphyPost.toImageModel(): ImageModel {
    return ImageModel(images.original.url)
}