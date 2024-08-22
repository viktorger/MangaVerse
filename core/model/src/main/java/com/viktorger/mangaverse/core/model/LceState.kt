package com.viktorger.mangaverse.core.model

sealed class LceState<out T> {
    data object Loading : LceState<Nothing>()
    class Content<T>(val data: T): LceState<T>()
    class Error(val throwable: Throwable): LceState<Nothing>()
}