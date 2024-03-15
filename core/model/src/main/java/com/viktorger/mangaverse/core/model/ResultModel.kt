package com.viktorger.mangaverse.core.model

sealed class ResultModel<out T> {
    class Success<T>(val data: T): ResultModel<T>()
    class Error(val e: Exception): ResultModel<Nothing>()
    data object Loading : ResultModel<Nothing>()
}