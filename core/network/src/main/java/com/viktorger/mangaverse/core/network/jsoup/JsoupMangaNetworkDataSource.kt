package com.viktorger.mangaverse.core.network.jsoup

import com.viktorger.mangaverse.common.util.BASE_URL
import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.model.ResultModel
import com.viktorger.mangaverse.core.network.MangaNetworkDataSource
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import javax.inject.Inject

class JsoupMangaNetworkDataSource @Inject constructor() : MangaNetworkDataSource {
    override suspend fun getShortcutsPage(page: Int): List<MangaShortcut> {
        val doc = Jsoup.connect("$BASE_URL/list?sortType=rate&page=1")
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .parser(Parser.xmlParser())
            .get()
        val lst = doc.select(".tile.col-sm-6")
        val mangaShortcutList: MutableList<MangaShortcut> = mutableListOf()

        lst.forEach {
            val title = it.select("h3 > a")
                .attr("title")
            val genres = it.select("div.tile-info > a.badge.badge-light")
                .text()
            val imgUrl = it.select("a > img").attr("data-original")
            val src = it.select("a").attr("href")

            mangaShortcutList.add(
                MangaShortcut(
                    detailsUrl = src,
                    title = title,
                    genres = genres,
                    imageUrl = imgUrl
                )
            )
        }

        return mangaShortcutList
    }

    override suspend fun getDetails(mangaUrl: String): ResultModel<MangaDetails> = callForResult {
        val doc = Jsoup.connect("$BASE_URL$mangaUrl")
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .parser(Parser.xmlParser())
            .get()

        val title = doc.select("h1.names > span.name").text()
        val desc = doc.select("div.manga-description").first()?.select("p,span,div")?.text()
        val genres = doc.select("p.elementList > a.badge.element-link")
            .joinToString(" ") { it.text() }
        val imageUrl = doc.select("div.picture-fotorama > img").first()?.attr("src")

        return@callForResult MangaDetails(
            title = title,
            description = desc ?: "",
            genres = genres,
            imageUrl = imageUrl ?: ""
        )
    }

    override suspend fun getChapters(mangaUrl: String): ResultModel<List<MangaChapter>> = callForResult {
        val doc = Jsoup.connect("$BASE_URL$mangaUrl")
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .parser(Parser.xmlParser())
            .get()

        val chapterList: MutableList<MangaChapter> = mutableListOf()
        doc.select("tr.item-row").forEach {
            val volume = it.attr("data-vol")

            val url = it.select("a.chapter-link").attr("href")
            val chapter = url.substringAfterLast("/")
            val date = it.select("td.date").attr("data-date")

            chapterList.add(MangaChapter(
                volume = volume,
                chapter = chapter,
                date = date,
                url = url
            ))
        }
        return@callForResult chapterList
    }

    private fun <T> callForResult(call: () -> T): ResultModel<T> = try {
        ResultModel.Success(call())
    } catch (e: Exception) {
        ResultModel.Error(e)
    }
}

