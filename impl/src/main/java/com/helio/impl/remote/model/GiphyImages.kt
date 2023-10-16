package com.helio.impl.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GiphyImages(
    @SerialName("original")
    val original: GiphyOriginalImage
)