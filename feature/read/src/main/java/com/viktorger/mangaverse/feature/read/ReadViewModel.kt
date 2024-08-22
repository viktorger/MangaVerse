package com.viktorger.mangaverse.feature.read

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktorger.mangaverse.core.data.repository.MangaRepository
import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.LceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReadViewModel(private val mangaRepository: MangaRepository) : ViewModel() {

    private val _chapterLiveData: MutableLiveData<LceState<MangaChapter>> =
        MutableLiveData(LceState.Loading)
    val chapterLiveData: LiveData<LceState<MangaChapter>> = _chapterLiveData

    private var chapterJob: Job? = null

    fun getChapter(chapterUrl: String) {
        chapterJob?.cancel()
        chapterJob = viewModelScope.launch (Dispatchers.Default) {
            val chapter = mangaRepository.getChapter(chapterUrl)
            _chapterLiveData.postValue(LceState.Content(chapter))
        }
    }
}