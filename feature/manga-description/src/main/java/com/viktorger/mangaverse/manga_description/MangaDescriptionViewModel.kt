package com.viktorger.mangaverse.manga_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktorger.mangaverse.core.data.repository.MangaRepository
import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.model.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MangaDescriptionViewModel(private val mangaRepository: MangaRepository) : ViewModel() {

    private val _detailsLiveData: MutableLiveData<ResultModel<MangaDetails>> =
        MutableLiveData(ResultModel.Loading)
    val detailsLiveData: LiveData<ResultModel<MangaDetails>> = _detailsLiveData

    private val _chaptersLiveData: MutableLiveData<ResultModel<List<MangaChapter>>> =
        MutableLiveData(ResultModel.Loading)
    val chaptersLiveData: LiveData<ResultModel<List<MangaChapter>>> = _chaptersLiveData

    private var detailsJob: Job? = null
    private var chaptersJob: Job? = null

    fun getMangaDescription(mangaUrl: String) {
        detailsJob?.cancel()
        detailsJob = viewModelScope.launch (Dispatchers.Default) {
            val details = mangaRepository.getDetails(mangaUrl)
            _detailsLiveData.postValue(details)
        }
    }

    fun getMangaChapters(mangaUrl: String) {
        chaptersJob?.cancel()
        chaptersJob = viewModelScope.launch (Dispatchers.Default) {
            val chapters = mangaRepository.getChapters(mangaUrl)
            _chaptersLiveData.postValue(chapters)
        }
    }

}