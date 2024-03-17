package com.viktorger.mangaverse.feature.home.navigation

import androidx.navigation.NavDirections

interface HomeNavigation {
    fun navigateToDescription(mangaUrl: String, navigate: (action: NavDirections) -> Unit)
}