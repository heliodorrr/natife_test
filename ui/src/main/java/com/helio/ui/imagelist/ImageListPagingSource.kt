package com.helio.ui.imagelist

import android.media.Image
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.helio.api.LoadImageListUseCase
import com.helio.api.model.ImageModel
import java.util.concurrent.ConcurrentHashMap

class ImageListPagingSource(
    private val useCase: LoadImageListUseCase,
    private val query: String,
): PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } ?: 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        val page = params.key ?: return LoadResult.Invalid()
        val offset = page * params.loadSize

        return getData(page, offset, params.loadSize)
    }

    private suspend fun getData(page: Int, offset: Int, limit: Int): LoadResult<Int, ImageModel> {
        return runCatching { useCase.invoke(query, offset, limit) }
                .getOrNull()?.let {
                    LoadResult.Page(
                        it,
                        prevKey = if (page > 0) page.dec() else null,
                        nextKey = page.inc()
                    )
                } ?: LoadResult.Invalid()

    }


}