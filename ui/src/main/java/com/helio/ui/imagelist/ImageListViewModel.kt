package com.helio.ui.imagelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.helio.api.LoadImageListUseCase
import com.helio.api.model.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.concatWith
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class ImageListViewModel @Inject constructor(
    private val loadImageListUseCase: LoadImageListUseCase
): ViewModel() {

    val queryStateFlow = MutableStateFlow("")

    val pagingDataFlow = queryStateFlow
        .debounce(0.5.seconds)
        .mapLatest { query->
            if (query.isEmpty()) {
                flowOf(PagingData.empty<ImageModel>())
            } else {
                flowOf(PagingData.empty<ImageModel>())
                    .onCompletion {
                        emitAll(
                            Pager(
                                config = PagingConfig(pageSize = 20),
                                pagingSourceFactory = {
                                    ImageListPagingSource(loadImageListUseCase, query)
                                }
                            ).flow
                        )
                    }
            }
        }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyFlow())




}