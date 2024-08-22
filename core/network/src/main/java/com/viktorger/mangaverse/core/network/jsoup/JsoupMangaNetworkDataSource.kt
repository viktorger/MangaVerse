package com.viktorger.mangaverse.core.network.jsoup

import com.viktorger.mangaverse.common.BASE_URL
import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaChapterShortcut
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.network.model.MangaDescription
import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.network.MangaNetworkDataSource
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
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

    override suspend fun getDetails(mangaUrl: String): MangaDetails {
        val doc = Jsoup.connect("$BASE_URL$mangaUrl")
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .parser(Parser.xmlParser())
            .get()

        val description = getDescription(doc)
        val chaptersShortcuts = getChaptersShortcuts(doc)

        return MangaDetails(
            title = description.title,
            genres = description.genres,
            description = description.description,
            imageUrl = description.imageUrl,
            mangaChaptersShortcuts = chaptersShortcuts
        )
    }

    private suspend fun getDescription(doc: Document): MangaDescription {
        val title = doc.select("h1.names > span.name").text()
        val desc = doc.select("div.manga-description").first()?.select("p,span,div")?.text()
        val genres = doc.select("p.elementList > a.badge.element-link")
            .joinToString(" ") { it.text() }
        val imageUrl = doc.select("div.picture-fotorama > img").first()?.attr("src")

        return MangaDescription(
            title = title,
            description = desc ?: "",
            genres = genres,
            imageUrl = imageUrl ?: ""
        )
    }

    private suspend fun getChaptersShortcuts(doc: Document):
            List<MangaChapterShortcut> = doc.select("tr.item-row").map {

        val volume = it.attr("data-vol")

        val url = it.select("a.chapter-link").attr("href")
        val chapter = url.substringAfterLast("/")
        val date = it.select("td.date").attr("data-date")

        MangaChapterShortcut(
            volume = volume,
            chapter = chapter,
            date = date,
            url = url
        )

    }

    override suspend fun getChapter(mangaUrl: String): MangaChapter {
        val doc = Jsoup.connect("$BASE_URL$mangaUrl")
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .parser(Parser.xmlParser())
            .get()

        val chapterTitle = doc.select("span.mobile-subtitle").text()

        val imageUrlList = getImageUrls(doc)

        val prevPageUrl = doc.select("span.input-group-prepend > a").attr("href").let {
            if (it.endsWith("page=last")) {
                it
            } else {
                null
            }
        }

        val nextPageUrl = doc.select("span.input-group-append > a").attr("href").let {
            if (it.endsWith("finish")) {
                null
            } else {
                it
            }
        }

        return MangaChapter(
            chapterTitle = chapterTitle,
            pagesUrls = imageUrlList,
            prevPageUrl = prevPageUrl,
            nextPageUrl = nextPageUrl
        )
    }

    private fun getImageUrls(doc: Document): List<String> {
        var regex = regexToFindAllImages()

        val lineWithImageUrl = regex.find(doc.toString())

        regex = regexToExtractBaseUrlAndFilePath()
        val urlObjects = lineWithImageUrl?.let {
            regex.findAll(it.value)
        } ?: sequenceOf()

        val imageUrlList = extractImageUrlsFromRegexResults(urlObjects)
        return imageUrlList
    }

    private fun extractImageUrlsFromRegexResults(
        urlObjects: Sequence<MatchResult>
    ): List<String> = urlObjects.map {
        val uncheckedBaseUrl = it.groups["baseUrl"]?.value ?: ""
        val baseUrl = uncheckedBaseUrl.ifBlank { BASE_URL }
        val filePath = it.groups["filePath"]?.value?.replace("amp;", "") ?: ""

        "$baseUrl$filePath"
    }.toList()

    private fun regexToExtractBaseUrlAndFilePath() =
        """\['(?<baseUrl>[^'"]*)','',"(?<filePath>[^'"]*)",\d+,\d+]""".toRegex()

    private fun regexToFindAllImages() = """rm_h\.readerDoInit.*""".toRegex()

}

