package com.loci.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1

class MyPagingSource : PagingSource<Int, User>() {

    init {
        Log.d("MyPagingSource", "init")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        Log.d("MyPagingSource", "load")
        Log.d("params.key", params.key.toString())
        Log.d("params.loadSize", params.loadSize.toString())

        val page = params.key ?: STARTING_KEY
        Log.d("page", page.toString())

        val range = page.until(page + params.loadSize)
        Log.d("range", range.toString())

        if (page != STARTING_KEY) {
            delay(3000)
        }

        return LoadResult.Page(
            data = range.map { number ->
                User(
                    id = number,
                    userName = "UserName is $number"
                )
            },
            prevKey = null,
            nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}