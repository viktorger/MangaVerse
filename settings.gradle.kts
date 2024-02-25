pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MangaVerse"
include(":app")
include(":feature:home")
include(":core:data")
include(":feature:library")
include(":core:designsystem")
include(":feature:manga-description")
include(":core:model")
include(":core:network")
include(":core:common")
include(":core:ui")
