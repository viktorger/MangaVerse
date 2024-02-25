package com.viktorger.mangaverse.core.network.jsoup

import android.util.Log
import com.viktorger.mangaverse.common.util.BASE_URL
import com.viktorger.mangaverse.core.model.MangaShortcut
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

            Log.d("JSOUP", imgUrl)

            mangaShortcutList.add(
                MangaShortcut(
                    url = src,
                    title = title,
                    genres = genres,
                    imageUrl = imgUrl
                )
            )
        }

        return mangaShortcutList
    }
}