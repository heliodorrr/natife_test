package com.helio.impl.di

import android.app.Application
import android.util.Log
import com.helio.api.LoadImageListUseCase
import com.helio.api.repository.ImageDataRepository
import com.helio.impl.Constants
import com.helio.impl.remote.GiphyApi
import com.helio.impl.remote.ImageDataRepositoryImpl
import com.helio.impl.usecase.LoadImageUseCaseImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
interface ImplModule {

    @Binds
    fun bindLoadImageUseCase(uc: LoadImageUseCaseImpl): LoadImageListUseCase

    @Binds
    fun bindImageDataRepository(repo: ImageDataRepositoryImpl): ImageDataRepository

    companion object {

        @Singleton
        @Provides
        fun provideGiphyApi(retrofit: Retrofit): GiphyApi {
            return retrofit.create(GiphyApi::class.java)
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(application: Application): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor { Log.d("OkHttp",it) }
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                )
                .addInterceptor { chain ->
                    val rb = chain.request().newBuilder()

                    val request = rb.url(
                        chain.request().url.newBuilder()
                            .addQueryParameter("api_key", Constants.GIPHY_API_KEY)
                            .build()
                    ).build()

                    chain.proceed(request)
                }
                .cache(
                    Cache(
                        application.cacheDir,
                    1024 * 1024 * 50
                    )
                )
                .build()
        }

        @Singleton
        @Provides
        fun provideJson(): Json = Json { ignoreUnknownKeys = true }

        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit {
            val mediaType = "application/json".toMediaType()

            return Retrofit.Builder()
                .baseUrl(Constants.GIPHY_API)
                .client(client)
                .addConverterFactory(json.asConverterFactory(mediaType))
                .build()
        }


    }

}