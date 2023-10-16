package com.helio.impl.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GiphyOriginalImage(
    @SerialName("url")
    val url: String
)