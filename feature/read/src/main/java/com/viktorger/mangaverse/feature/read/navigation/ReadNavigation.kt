package com.viktorger.mangaverse.feature.read.navigation

import android.os.Bundle

interface ReadNavigation {
    fun getChapterUrl(arguments: Bundle): String
}