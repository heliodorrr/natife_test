package com.helio.impl.remote

import com.helio.impl.Constants
import com.helio.impl.remote.model.GiphyEnvelope
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET(Constants.GIPHY_API_SEARCH)
    suspend fun loadByQuery(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): GiphyEnvelope

}